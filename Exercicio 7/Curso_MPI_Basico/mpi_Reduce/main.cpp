#include <iostream>
#include <mpi.h>

#define MAX_LENGHT  10

using namespace std;

/*********************************************************************

    Programa para teste da subrotina MPI_Reduce

    15/03/2012

    Programa original criado em fortran na obra
    RS/6000 SP: Practical MPI Programming
    e traduzido para c++  por Onofre Felix de Lima.
    para compilar.

    para compilar
    mpic++ -o  mpireduce.bin main.cpp

    para executar
    mpirun -np 2 mpireduce.bin

*************************************************************************/





int main(int argc, char **argv)
{
    int size;
    int myrank;
    int recv_msg;
    int send_msg;



    MPI_Status status;
    MPI_Init(&argc,&argv);
    MPI_Comm_size(MPI_COMM_WORLD, &size );
    MPI_Comm_rank( MPI_COMM_WORLD, &myrank);



    send_msg = myrank+1;
    recv_msg = 0;

//    for (int i= 0; i < size; i++ )
//    {
//        recv_msg[i] = 0;
//    }

    std::cout << " Antes da chamada de MPI_Reduce " << std::endl;
    std::cout << "Processo (rank) " << " send_msg   "<<  "  recv_msg  " << std::endl;
   // for (int i= 0; i < size; i++ )
        std::cout << "    " << myrank <<"              " <<  send_msg << "             " << recv_msg << std::endl;



//    if (myrank == 0)
//       for (int i= 0; i < size; i++ )
//          recv_msg[i] = i;





    MPI_Reduce(&send_msg,&recv_msg,1, MPI_INT, MPI_SUM,0,MPI_COMM_WORLD);



    std::cout << " Após a chamada de MPI_Reduce " << std::endl;
    std::cout << "Processo (rank) "   "recv_msg  " << std::endl;
  //  for (int i= 0; i < size; i++ )

        std::cout << "    " << myrank <<"              "  << recv_msg << std::endl;






   MPI_Finalize ();

}
