#include <iostream>
#include <mpi.h>

#define MAX_LENGHT  10

using namespace std;

/*********************************************************************

    Programa para teste da subrotina MPI_Alltoall

    15/03/2012

    Programa original criado em fortran na obra
    RS/6000 SP: Practical MPI Programming
    e traduzido para c++  por Onofre Felix de Lima.
    para compilar.

    para compilar
    mpic++ -o  mpialltoall.bin main.cpp

    para executar
    mpirun -np 2 mpialltoall.bin

*************************************************************************/





int main(int argc, char **argv)
{
    int size;
    int myrank;
    int send_msg[MAX_LENGHT];
    int recv_msg[MAX_LENGHT];



    MPI_Status status;
    MPI_Init(&argc,&argv);
    MPI_Comm_size(MPI_COMM_WORLD, &size );
    MPI_Comm_rank( MPI_COMM_WORLD, &myrank);



    //int isend = myrank+1;

    for (int i= 0; i < size; i++ )
    {
        send_msg[i] = i+size*myrank;
        recv_msg[i] = 0;
    }

    std::cout << " Antes da chamada de MPI_Alltoall " << std::endl;
    std::cout << "Processo (rank) "  << " i " << " Vetor send_msg[i]  " << " Vetor recv_msg[i]  " << std::endl;
    for (int i= 0; i < size; i++ ){
        std::cout << "    " << myrank <<"            "    << i    <<"              "<< send_msg[i]<<"              "<< recv_msg[i]<< std::endl;
    }



//    if (myrank == 0)
//       for (int i= 0; i < size; i++ )
//          iallgather_msg[i] = i;





    MPI_Alltoall(send_msg,1, MPI_INT, recv_msg,1,MPI_INT,MPI_COMM_WORLD);



    std::cout << " Após a chamada de MPI_Alltoall " << std::endl;
    std::cout << "Processo (rank) "  << " i " << " Vetor iAllgather_msg  " << std::endl;
    for (int i= 0; i < size; i++ )

        std::cout << "    " << myrank <<"            "    << i    <<"              "<< recv_msg[i]<< std::endl;






   MPI_Finalize ();

}
