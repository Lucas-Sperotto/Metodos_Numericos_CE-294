#include <math.h>
#include <stdio.h>
#include <iostream>
#include <vector>
using namespace std;
#define Pi 3.14159265358979323846e0
struct MALHA
{
    int Ngrid; //numero de pt.s ao longo de rho
    double prec; // toler^ancia de c´alculo
// (determina sa´ida do c´odigo)
    int Nrho, Nz; //numero de pontos em rho e em z
    double del; //dens. radial e vert. da malha
    int K,I; // N´umeros K e I (ver eqs. 10)
    void LeParametrosDaMalha
    (int NN, double precisao);
};
//dimens~oes do cilindro
struct CILINDRO
{
    double Rext, Rint; // raio ext. e int.
    double Lext, Lint; // comprimentos ext. e int.
    double Vext, Vint; //Potenciais ext. e int.
    MALHA Grid;
    vector <vector <double>> U; //campo de pot.
    void LePotenciais(double V1, double V2);
    void LeGeometria (double R1, double R2,
                      double L1, double L2);
    void GeraMalha(); //Gera malha para o pot. e
// aplica condi¸c~oes de fronteira fixas
    double CalculaResiduo(int i, int k);
    void SORLaplace(double w);
// w par^am. de relax.
// de acordo com o potencial
    void EscreveU();
};
