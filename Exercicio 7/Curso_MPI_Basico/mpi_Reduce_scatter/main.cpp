#include <iostream>
#include <mpi.h>

#define MAX_LENGHT  10

using namespace std;

/*********************************************************************

    Programa para teste da subrotina MPI_Reducescatter

    15/03/2012

    Programa original criado em fortran na obra
    RS/6000 SP: Practical MPI Programming
    e traduzido para c++  por Onofre Felix de Lima.
    para compilar.

    para compilar
    mpic++ -o  mpireducescatter.bin main.cpp

    para executar
    mpirun -np 2 mpireducescatter.bin

*************************************************************************/





int main(int argc, char **argv)
{
    int size;
    int myrank;
    int recv_msg[MAX_LENGHT];
    int send_msg[MAX_LENGHT];
    int ircnt[]={1,2,3};



    MPI_Status status;
    MPI_Init(&argc,&argv);
    MPI_Comm_size(MPI_COMM_WORLD, &size );
    MPI_Comm_rank( MPI_COMM_WORLD, &myrank);





    for (int i= 0; i < 6; i++ )
    {
        send_msg[i] = (i+1)+myrank*10;
        recv_msg[i] = 0;
    }

    std::cout << " Antes da chamada de MPI_Reducescatter " << std::endl;
    std::cout << "Processo (rank) " << " send_msg   "<<  "  recv_msg  " << std::endl;
    for (int i= 0; i < 6; i++ )
        std::cout << "    " << myrank <<"              " <<  send_msg[i] << "             " << recv_msg[i] << std::endl;



//    if (myrank == 0)
//       for (int i= 0; i < size; i++ )
//          recv_msg[i] = i;





    MPI_Reduce_scatter(&send_msg,&recv_msg,ircnt, MPI_INT, MPI_SUM,MPI_COMM_WORLD);



    std::cout << " Após a chamada de MPI_Scan " << std::endl;
    std::cout << "Processo (rank) "   "recv_msg  " << std::endl;
    for (int i= 0; i < 3; i++ )

        std::cout << "    " << myrank <<"              "  << recv_msg[i] << std::endl;






   MPI_Finalize ();

}
