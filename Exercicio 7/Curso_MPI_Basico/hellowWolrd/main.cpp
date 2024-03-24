#include <iostream>
#include <mpi.h>

/*********************************************************************

 fonte: MPI: A MESSAGE-PASSING INTERFACE STANDARD, VERSION 1.3
        MESSAGE PASSING FORUM.

 Este programa envia uma mensagem de texto para um segundo nó
 compilar: mpic++ -o bw.bin  main.cpp;
 executar: mpirun -np 2 hw.bin
 **********************************************************************/




using namespace std;

int main(int argc, char **argv)
{
    char message[20];
    int myrank;
    MPI_Status status;
    MPI_Init(&argc,&argv);
    MPI_Comm_rank(MPI_COMM_WORLD,&myrank);
    if (myrank == 0 )
    {
        strcpy (message,"Hellow Wolrd");
        MPI_Send(message,strlen(message)+1,MPI_CHAR,1,99,MPI_COMM_WORLD);
    }
    else{
        MPI_Recv(message,20,MPI_CHAR,0,99,MPI_COMM_WORLD,&status);

        std::cout << " Rank: "  << myrank << std::endl;
        std::cout << " Recebimento da Mensagem: "  << message << std::endl;

    }


    MPI_Finalize();
}
