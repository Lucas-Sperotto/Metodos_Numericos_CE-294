#include "defs.h"
//Le par^ametros externos da malha
//N numero de pontos na malha
//prec :: precis~ao esperada para o c´alculo
// da solu¸c~ao
void MALHA::LeParametrosDaMalha (int NN,
                                 double precisao)
{
    Ngrid=NN;
    prec=precisao;
}
//Le par^ametros externos (potenciais)
void CILINDRO::LePotenciais
(double V1, double V2 )
{
    Vext=V2;
    Vint=V1;
}
//Le par^ametros geom´etricos do cilindro
//R1 ´e o raio interno
//R2 ´e o raio interno (R2 >R1)
//L ´e o comprimento (L>0)
void CILINDRO::LeGeometria (double R1,
                            double R2, double L1, double L2)
{
    Rint=R1;
    Rext=R2;
    Lint=L1;
    Lext=L2;
}
void CILINDRO::GeraMalha()
{
    int k, i, I, K;
    Grid.del = Rext/(double)Grid.Ngrid;
//Densidade da malha
//calculo de inteiros aproximados
//para dimensionamento da malha
    Grid.Nrho = Grid.Ngrid;
    Grid.Nz = (int)(Lext/(2.*Grid.del));
// aloca mem´oria para a matriz do
// campo de pot. na malha
    U.resize(Grid.Nrho);
    for(i=0; i<Grid.Nrho; i++)
        U[i].resize(Grid.Nz);
//Condi¸c~oes de Neumann para o cilindro ext.
    for(k=0; k<Grid.Nz; k++)
        U[Grid.Nrho-1][k]=Vext;
//Condi¸c~oes de Neumann para a tampa ext.
    for(i=0; i<Grid.Nrho; i++)
        U[i][Grid.Nz-1]=Vext;
//Condi¸c~oes para o cilindro interno
//Primeiro calcula os n´umeros K e I
    Grid.I=(int)(Rint/Grid.del);
    Grid.K=(int)(Lint/(2.*Grid.del));
    I=Grid.I;
    K=Grid.K;
//condi¸c~ao de Neumann para o cilindro int.
    for(k=0; k<=K; k++)
        U[I][k]=Vint;
//condi¸c~ao de Neumann para a tampa int.
    for(i=0; i<=I; i++)
        U[i][K]=Vint;
//Gera vals. iniciais para o campo de pot.
// baseado nos potencias das armaduras
//regi~ao A
    for(i=0; i<=I; i++)
        for(k=K+1; k<Grid.Nz-1; k++)
            U[i][k]=.5*(Vext+Vint);
//regi~ao B
    for(i=I+1; i<Grid.Nrho-1; i++)
        for(k=0; k<Grid.Nz-1; k++)
            U[i][k]=.5*(Vext+Vint);
//regi~ao interior ao cilindro int.
    for(i=0; i<I; i++)
        for(k=0; k<K; k++)
            U[i][k]=Vint;
}
//Calcula res´iduo da eq. de Laplace
// no ponto (i,k) da malha, Eq. (??)
double CILINDRO::CalculaResiduo
(int i, int k)
{
    double gm=1.+.5/(double)i;
    double gn=1.-.5/(double)i;
    return U[i+1][k]*gm + U[i-1][k]*gn-4.*U[i][k]
           + U[i][k+1] + U[i][k-1];
}
void CILINDRO::EscreveU()
{
    int i,k;
    FILE *fp;
    fp = fopen("U.dat", "w"); //open file to write
    for(i=0; i<Grid.Nrho; i++)
    {
        for(k=0; k<Grid.Nz; k++)
        {
            fprintf(fp," %5.3f ",U[i][k]);
        }
        fprintf(fp,"\n");
    }
    fclose(fp);
}
//Resolve a eq. de Laplace pelo m´etodo SOR
//res: vari´avel auxiliar para o res´iduo
//resmax: m´aximo res´iduo
void CILINDRO::SORLaplace(double w)
{
    int i,k;
    double res, resmax;
    do
    {
        resmax=0.;
// aplica condi¸c~ao de Dirichlet dU/dRho=0
// para simetria axial (Eq. 12)
        for(k=Grid.K+1; k<Grid.Nz-1; k++)
            U[0][k]=U[1][k];
// aplica condi¸c~ao de Dirichlet para
// fronteira dU/dz=0 (Eq. 12)
        for(i=Grid.I+1; i<Grid.Nrho-1; i++)
            U[i][0]=U[i][1];
//Itera¸c~ao para Regi~ao A
        for(i=1; i<=Grid.I; i++)
            for(k=Grid.K+1; k<Grid.Nz-1; k++)
            {
                res=CalculaResiduo(i,k);
                U[i][k]=U[i][k] + .25*w*res;
                if (fabs(res)>resmax) resmax=fabs(res);
            }
//Itera¸c~ao para Regi~ao B
        for(i=Grid.I+1; i<Grid.Nrho-1; i++)
            for(k=1; k<Grid.Nz-1; k++)
            {
                res=CalculaResiduo(i,k);
                U[i][k]=U[i][k]+.25*w*res;
                if (fabs(res)>resmax) resmax=fabs(res);
            }
    }
    while (fabs(resmax)>Grid.prec);
    cout << "Terminou..." << endl;
    EscreveU();
}
