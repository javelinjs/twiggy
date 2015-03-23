package me.yzhi.twiggy.util

/**
 * static std::atomic<int64> g_mem_usage_sarray = ATOMIC_VAR_INIT(0);
 * extern int64 g_mem_usage_sarray;
 * extern std::mutex g_mu_sa_;

 * Memory efficient array. Most operations are zero-copy, such as assign, slice
 * a segment, convert to Eigen3 vector/array. It shares the same semantic as a C
 * array pointer. For example,
 * SArray<int> A(10);
 * SArray<int> B = A;
 * SArray<int> C = A.segment(SizeR(1,3));
 * A[2] = 2;
 * then B[2] == 2 and C[1] == 2 too.
 */
class SharedArray {
}
