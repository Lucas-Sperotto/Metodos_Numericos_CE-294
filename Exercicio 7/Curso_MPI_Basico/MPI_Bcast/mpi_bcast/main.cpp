#include <iostream>
#include <mpi.h>

#define MAX_LENGHT  4

using namespace std;

/*********************************************************************

    Programa para teste da subrotina MPI_Bcast.

    14/03/2012

    Programa original criado em fortran na obra
    RS/6000 SP: Practical MPI Programming
    e traduzido para c++  por Onofre Felix de Lima.
    para compilar.

    para compilar
    mpic++ -o  mpibcast.bin main.cpp

    para executar
    mpirun -np 2 mpibcast.bin

*************************************************************************/





int main(int argc, char **argv)
{
    int size;
    int myrank;
    int ibcast_msg[MAX_LENGHT];



    MPI_Status status;
    MPI_Init(&argc,&argv);
    MPI_Comm_size(MPI_COMM_WORLD, &size );
    MPI_Comm_rank( MPI_COMM_WORLD, &myrank);



    if (myrank == 0)
        for (int i = 0; i < MAX_LENGHT;i++)
            ibcast_msg[i]= i;
    else
        for (int i = 0; i < MAX_LENGHT;i++)
            ibcast_msg[i]= 0;


    std::cout << "Size: " << size;
    std::cout << " Rank: " << myrank << std::endl;

    std::cout << "Antes do envio da Mensagem MPI_Bcast ";
    for (int i = 0; i < MAX_LENGHT;i++)
            std::cout << ibcast_msg[i] << "  " ;
    std::cout << std::endl;

    MPI_Bcast(ibcast_msg,MAX_LENGHT,MPI_INT,0,MPI_COMM_WORLD);


    std::cout << "Após o  BroadCast ";
    for (int i = 0; i < MAX_LENGHT;i++)
            std::cout << ibcast_msg[i] << "  " ;
    std::cout << std::endl;




    MPI_Finalize ();

}
