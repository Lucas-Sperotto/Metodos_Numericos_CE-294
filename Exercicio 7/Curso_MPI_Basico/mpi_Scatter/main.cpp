#include <iostream>
#include <mpi.h>

#define MAX_LENGHT  10

using namespace std;

/*********************************************************************

    Programa para teste da subrotina MPI_Scatter.

    15/03/2012

    Programa original criado em fortran na obra
    RS/6000 SP: Practical MPI Programming
    e traduzido para c++  por Onofre Felix de Lima.
    para compilar.

    para compilar
    mpic++ -o  mpiscatter.bin main.cpp

    para executar
    mpirun -np 2 mpiscatter.bin

*************************************************************************/





int main(int argc, char **argv)
{
    int size;
    int myrank;
    int iscatter_msg[MAX_LENGHT];
    int recv;



    MPI_Status status;
    MPI_Init(&argc,&argv);
    MPI_Comm_size(MPI_COMM_WORLD, &size );
    MPI_Comm_rank( MPI_COMM_WORLD, &myrank);



    int isend = myrank+1;

    for (int i= 0; i < MAX_LENGHT; i++ )
    {
        iscatter_msg[i] = 0;
    }


    if (myrank == 0)
       for (int i= 0; i < size; i++ )
          iscatter_msg[i] = i;





    MPI_Scatter(&iscatter_msg[0],1, MPI_INT, &recv,1,MPI_INT,0,MPI_COMM_WORLD);



    std::cout << "Processo (rank) " << myrank <<"    " << "Impressão de recv " << recv<< std::endl;



   MPI_Finalize ();

}
