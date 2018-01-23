package io.sunglass.uv.nativelib

import io.sunglass.uv.nativelib.filesystem.lib.{uv_gid_t, uv_uid_t}
import io.sunglass.uv.nativelib.loop.lib._
import io.sunglass.uv.nativelib.process.lib._
import io.sunglass.uv.nativelib.stream.lib.uv_stream_t
import io.sunglass.uv.nativelib.utils.lib.uv_pid_t

import scala.scalanative.native._

package object process {

  val UV_IGNORE: uv_stdio_flags = 0x00.asInstanceOf[uv_stdio_flags]
  val UV_CREATE_PIPE: uv_stdio_flags = 0x01.asInstanceOf[uv_stdio_flags]
  val UV_INHERIT_FD: uv_stdio_flags = 0x02.asInstanceOf[uv_stdio_flags]
  val UV_INHERIT_STREAM: uv_stdio_flags = 0x04.asInstanceOf[uv_stdio_flags]
  val UV_READABLE_PIPE: uv_stdio_flags = 0x10.asInstanceOf[uv_stdio_flags]
  val UV_WRITABLE_PIPE: uv_stdio_flags = 0x20.asInstanceOf[uv_stdio_flags]

  val UV_PROCESS_SETUID: uv_process_flags = (1 << 0).asInstanceOf[uv_process_flags]
  val UV_PROCESS_SETGID: uv_process_flags = (1 << 1).asInstanceOf[uv_process_flags]
  val UV_PROCESS_WINDOWS_VERBATIM_ARGUMENTS: uv_process_flags = (1 << 2).asInstanceOf[uv_process_flags]
  val UV_PROCESS_DETACHED: uv_process_flags = (1 << 3).asInstanceOf[uv_process_flags]
  val UV_PROCESS_WINDOWS_HIDE: uv_process_flags = (1 << 4).asInstanceOf[uv_process_flags]

  @link("uv")
  @extern
  object lib {
    type uv_process_t = extern
    type uv_process_flags = CUnsignedInt
    type uv_exit_cb = CFunctionPtr3[Ptr[uv_process_t], CLongLong, CInt, Unit]
    type uv_stdio_flags = extern
    type uv_stdio_container_t = CStruct2[
      uv_stdio_flags, //flags
      CInt //Data
      ]


    type uv_process_options_t = CStruct10[
      uv_exit_cb, // exit_cb;
      CString, // file;
      Ptr[CString], //args;
      Ptr[CString], //;env;
      CString, //cwd;
      uv_process_flags, // flags;
      CInt, // stdio_count;
      Ptr[uv_stdio_container_t], // * stdio;
      uv_uid_t, // uid;
      uv_gid_t // gid;
      ]
    type uv_process_cb = CFunctionPtr1[Ptr[uv_process_t], Unit]

    def uv_disable_stdio_inheritance(): Unit = extern

    def uv_spawn(loop: Ptr[uv_loop_t], handle: Ptr[uv_process_t], options: Ptr[uv_process_options_t]): CInt = extern

    def uv_process_kill(process: Ptr[uv_process_t], signum: CInt): CInt = extern

    def uv_process_get_pid(process: Ptr[uv_process_t]): uv_pid_t = extern
  }

  implicit class stdioContainer(val ptr: Ptr[uv_stdio_container_t]) extends AnyVal {
    def flags: uv_stdio_flags = !ptr._1

    def flags_=(flags: uv_stdio_flags): Unit = !ptr._1 = flags

    def stream: Ptr[uv_stream_t] = (!ptr._2).cast[Ptr[uv_stream_t]]

    def stream_=(handle: Ptr[uv_stream_t]): Unit = !ptr._2 = handle.cast[CInt]

    def fd: CInt = !ptr._2

    def fd_=(fd: CInt): Unit = !ptr._2 = fd
  }

  implicit class processOption(val ptr: Ptr[uv_process_options_t]) extends AnyVal {
    def exit_cb: uv_exit_cb = !ptr._1

    def exit_cb_=(v: uv_exit_cb): Unit = !ptr._1 = v

    def file: CString = !ptr._2

    def file_=(v: CString): Unit = !ptr._2 = v

    def args: Ptr[CString] = !ptr._3

    def args_=(v: Ptr[CString]): Unit = !ptr._3 = v

    def env: Ptr[CString] = !ptr._4

    def env_=(v: Ptr[CString]): Unit = !ptr._4 = v

    def cwd: CString = !ptr._5

    def cwd_=(v: CString): Unit = !ptr._5 = v

    def flags: uv_process_flags = !ptr._6

    def flags_=(v: uv_process_flags): Unit = !ptr._6 = v

    def stdio_count: CInt = !ptr._7

    def stdio_count_=(v: CInt): Unit = !ptr._7 = v

    def stdio: Ptr[uv_stdio_container_t] = !ptr._8

    def stdio_=(v: Ptr[uv_stdio_container_t]): Unit = !ptr._8 = v

    def uid: uv_uid_t = !ptr._9

    def uid_=(v: uv_uid_t): Unit = !ptr._9 = v

    def gid: uv_gid_t = !ptr._10

    def gid_=(v: uv_gid_t): Unit = !ptr._10 = v

  }

}
