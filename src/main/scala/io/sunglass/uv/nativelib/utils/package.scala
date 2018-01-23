package io.sunglass.uv.nativelib

import io.sunglass.uv.nativelib.handle.lib.uv_handle_type
import io.sunglass.uv.nativelib.loop.lib.uv_loop_t
import io.sunglass.uv.nativelib.utils.lib.{uv_rusage_t, uv_timeval_t}

import scala.scalanative.native._
import scala.scalanative.native.stdio.FILE
import scala.scalanative.posix.netinet.in.{sockaddr_in, sockaddr_in6}

package object utils {

  @link("uv")
  @extern
  object lib {
    type uv_timeval_t = extern
    type uv_buf_t = extern
    type uv_file = extern
    type uv_os_sock_t = extern
    type uv_os_fd_t = extern
    type uv_pid_t = extern
    type uv_rusage_t = CStruct16[
      uv_timeval_t, /* user CPU time used */
      uv_timeval_t, /* system CPU time used */
      CUnsignedLongLong, /* maximum resident set size */
      CUnsignedLongLong, /* integral shared memory size */
      CUnsignedLongLong, /* integral unshared data size */
      CUnsignedLongLong, /* integral unshared stack size */
      CUnsignedLongLong, /* page reclaims (soft page faults) */
      CUnsignedLongLong, /* page faults (hard page faults) */
      CUnsignedLongLong, /* swaps */
      CUnsignedLongLong, /* block input operations */
      CUnsignedLongLong, /* block output operations */
      CUnsignedLongLong, /* IPC messages sent */
      CUnsignedLongLong, /* IPC messages received */
      CUnsignedLongLong, /* signals received */
      CUnsignedLongLong, /* voluntary context switches */
      CUnsignedLongLong /* involuntary context switches */
      ]
    type uv_cpu_info_t = extern
    type uv_interface_address_t = extern
    type uv_passwd_t = extern
    type uv_malloc_func = CFunctionPtr1[CSSize, Ptr[Byte]]
    type uv_realloc_func = CFunctionPtr2[Ptr[Byte], CSSize, Ptr[Byte]]
    type uv_calloc_func = CFunctionPtr2[CSSize, CSSize, Ptr[Byte]]
    type uv_free_func = CFunctionPtr1[Ptr[Byte], Ptr[Byte]]

    def uv_buf_init(base: CString, len: CUnsignedInt): uv_buf_t = extern

    def uv_setup_args(argc: CInt, argv: Ptr[CString]): Ptr[CString] = extern

    def uv_get_process_title(buffer: CString, size: CSize): CInt = extern

    def uv_set_process_title(buffer: CString): CInt = extern

    def uv_resident_set_memory(rss: Ptr[CSize]): CInt = extern

    def uv_getrusage(rusage: Ptr[uv_rusage_t]): CInt = extern

    def uv_os_getpid(): uv_pid_t = extern

    def uv_os_getppid(): uv_pid_t = extern

    def uv_cpu_info(cpu_infos: Ptr[Ptr[uv_cpu_info_t]], count: Ptr[CInt]): CInt = extern

    def uv_free_cpu_info(cpu_infos: Ptr[Ptr[uv_cpu_info_t]], count: CInt): Unit = extern

    def uv_interface_addresses(addresses: Ptr[Ptr[uv_interface_address_t]], count: Ptr[CInt]): CInt = extern

    def uv_free_interface_addresses(addresses: Ptr[Ptr[uv_interface_address_t]], count: CInt): Unit = extern

    def uv_loadavg(avg: Ptr[Double]): Unit = extern

    def uv_uptime(uptime: Ptr[Double]): UInt = extern


    def uv_guess_handle(file: uv_file): uv_handle_type = extern

    def uv_ip4_addr(ip: CString, port: CInt, addr: Ptr[sockaddr_in]): CInt =
      extern


    def uv_ip6_addr(ip: CString, port: CInt, addr: Ptr[sockaddr_in6]): CInt =
      extern

    def uv_ip4_name(src: Ptr[sockaddr_in], dst: CString, ize: CSize): CInt =
      extern


    def uv_ip6_name(isrc: Ptr[sockaddr_in6], dst: CString, ize: CSize): CInt =
      extern

    def uv_inet_ntop(af: CInt, src: Ptr[Byte], dst: CString, size: CSize): CInt = extern

    def uv_inet_pton(af: CInt, src: CString, dst: Ptr[Byte]): CInt = extern

    def uv_if_indextoname(ifindex: CUnsignedInt, buffer: CString, size: Ptr[CSize]): CInt = extern

    def uv_if_indextoiid(ifindex: CUnsignedInt, buffer: CString, size: Ptr[CSize]): CInt = extern

    def uv_execpath(buffer: CString, size: Ptr[CSize]): CInt = extern

    def uv_cwd(buffer: CString, size: Ptr[CSize]): CInt = extern

    def uv_chdir(dir: CString): CInt = extern

    def uv_os_homedir(buffer: CString, size: Ptr[CSize]): CInt = extern

    def uv_os_tmpdir(buffer: CString, size: Ptr[CSize]): CInt = extern

    def uv_os_get_passwd(pwd: Ptr[uv_passwd_t]): CInt = extern

    def uv_os_free_passwd(pwd: Ptr[uv_passwd_t]): CInt = extern

    def uv_get_total_memory(): CUnsignedLongLong = extern

    def uv_hrtime(): CUnsignedLongLong = extern

    def uv_print_all_handles(loop: Ptr[uv_loop_t], tream: Ptr[FILE]): CInt = extern

    def uv_print_active_handles(loop: Ptr[uv_loop_t], tream: Ptr[FILE]): CInt = extern

    def uv_os_getenv(name: CString, buffer: CString, size: Ptr[CSize]): CInt = extern

    def uv_os_setenv(name: CString, value: CString): CInt = extern

    def uv_os_sunetenv(name: CString): CInt = extern

    def uv_os_gethostname(name: CString, size: CSize): CInt = extern

    def uv_replace_allocator(malloc_func: uv_malloc_func,
                             realloc_func: uv_realloc_func,
                             calloc_func: uv_calloc_func,
                             free_func: uv_free_func
                            ): CInt = extern
  }

  implicit class uvRusageT(val ptr: Ptr[uv_rusage_t]) extends AnyVal {


    def maxrss: CUnsignedLongLong = !ptr._3

    def ixrss: CUnsignedLongLong = !ptr._4

    def idrss: CUnsignedLongLong = !ptr._5

    def isrss: CUnsignedLongLong = !ptr._6

    def minflt: CUnsignedLongLong = !ptr._7

    def majflt: CUnsignedLongLong = !ptr._8

    def swap: CUnsignedLongLong = !ptr._9
  }

}
