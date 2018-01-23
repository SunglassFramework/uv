package io.sunglass.uv.nativelib

import io.sunglass.uv.nativelib.loop.lib._
import io.sunglass.uv.nativelib.poll.lib.uv_poll_event
import io.sunglass.uv.nativelib.utils.lib.uv_os_sock_t

import scala.scalanative.native._

package object poll {

  val UV_READABLE: uv_poll_event = 1.asInstanceOf[uv_poll_event]
  val UV_WRITABLE: uv_poll_event = 2.asInstanceOf[uv_poll_event]
  val UV_DISCONNECT: uv_poll_event = 4.asInstanceOf[uv_poll_event]
  val UV_PRIORITIZED: uv_poll_event = 8.asInstanceOf[uv_poll_event]

  @link("uv")
  @extern
  object lib {
    type uv_poll_t = extern
    type uv_poll_event = extern
    type uv_poll_cb = CFunctionPtr3[Ptr[uv_poll_t], CInt, CInt, Unit]

    def uv_poll_init(loop: Ptr[uv_loop_t], handle: Ptr[uv_poll_t], fd: CInt): CInt = extern

    def uv_poll_init_socket(loop: Ptr[uv_loop_t], handle: Ptr[uv_poll_t], socket: uv_os_sock_t): CInt = extern

    def uv_poll_start(poll: Ptr[uv_poll_t], cb: uv_poll_cb): CInt = extern

    def uv_poll_stop(poll: Ptr[uv_poll_t]): CInt = extern
  }

}
