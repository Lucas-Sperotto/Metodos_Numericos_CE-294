#include <iostream>
#include <mpi.h>

using namespace std;

int main(int argc, char **argv)
{
    int size;
    int myrank;

    MPI_Status status;
    MPI_Init(&argc,&argv);
    MPI_Comm_size(MPI_COMM_WORLD, &size );
    MPI_Comm_rank( MPI_COMM_WORLD, &myrank);

    std::cout << "Size: " << size;
    std::cout << " Rank: " << myrank << std::endl;

    MPI_Finalize ();

}
