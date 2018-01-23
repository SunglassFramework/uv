package io.sunglass.uv.nativelib

import io.sunglass.uv.nativelib.loop.lib._
import io.sunglass.uv.nativelib.tty.lib.uv_tty_mode_t
import io.sunglass.uv.nativelib.utils.lib.uv_file

import scala.scalanative.native._

package object tty {
  val UV_TTY_MODE_NORMAL: uv_tty_mode_t = 0.asInstanceOf[uv_tty_mode_t]
  val UV_TTY_MODE_RAW: uv_tty_mode_t = 1.asInstanceOf[uv_tty_mode_t]
  val UV_TTY_MODE_IO: uv_tty_mode_t = 2.asInstanceOf[uv_tty_mode_t]

  @link("uv")
  @extern
  object lib {
    type uv_tty_t = extern
    type uv_tty_mode_t = extern
    type uv_tty_cb = CFunctionPtr1[Ptr[uv_tty_t], Unit]

    def uv_tty_init(loop: Ptr[uv_loop_t], handle: Ptr[uv_tty_t], fd: uv_file, readable: CInt): CInt = extern

    def uv_tty_set_mode(handle: Ptr[uv_tty_t], mode: uv_tty_mode_t): CInt = extern

    def uv_tty_reset_mode(): CInt = extern

    def uv_tty_get_winsize(handle: Ptr[uv_tty_t], width: Ptr[CInt], height: Ptr[CInt]): CInt = extern
  }

}
