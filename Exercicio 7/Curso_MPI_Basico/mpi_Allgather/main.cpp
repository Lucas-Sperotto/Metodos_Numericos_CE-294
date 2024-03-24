#include <iostream>
#include <mpi.h>

#define MAX_LENGHT  10

using namespace std;

/*********************************************************************

    Programa para teste da subrotina MPI_Allgather

    15/03/2012

    Programa original criado em fortran na obra
    RS/6000 SP: Practical MPI Programming
    e traduzido para c++  por Onofre Felix de Lima.
    para compilar.

    para compilar
    mpic++ -o  mpiallgather.bin main.cpp

    para executar
    mpirun -np 2 mpiallgather.bin

*************************************************************************/





int main(int argc, char **argv)
{
    int size;
    int myrank;
    int recv_msg[MAX_LENGHT];
    int send_msg;



    MPI_Status status;
    MPI_Init(&argc,&argv);
    MPI_Comm_size(MPI_COMM_WORLD, &size );
    MPI_Comm_rank( MPI_COMM_WORLD, &myrank);



    send_msg = myrank+1;

    for (int i= 0; i < size; i++ )
    {
        recv_msg[i] = 0;
    }

    std::cout << " Antes da chamada de MPI_Allgather " << std::endl;
    std::cout << "Processo (rank) " << " send_msg   "<< " i " << " Vetor recv_msg[i]  " << std::endl;
    for (int i= 0; i < size; i++ )
        std::cout << "    " << myrank <<"              " <<  send_msg << "         "       << i    <<"              "<< recv_msg[i]<< std::endl;



//    if (myrank == 0)
//       for (int i= 0; i < size; i++ )
//          recv_msg[i] = i;





    MPI_Allgather(&send_msg,1, MPI_INT, recv_msg,1,MPI_INT,MPI_COMM_WORLD);



    std::cout << " Após a chamada de MPI_Allgather " << std::endl;
    std::cout << "Processo (rank) "  << " i " << " Vetor recv_msg[i]  " << std::endl;
    for (int i= 0; i < size; i++ )

        std::cout << "    " << myrank <<"            "    << i    <<"              "<< recv_msg[i]<< std::endl;






   MPI_Finalize ();

}
