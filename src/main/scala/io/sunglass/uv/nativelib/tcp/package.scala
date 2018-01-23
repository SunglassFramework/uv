package io.sunglass.uv.nativelib

import io.sunglass.uv.nativelib.loop.lib._
import io.sunglass.uv.nativelib.request.lib.uv_req_t
import io.sunglass.uv.nativelib.stream.lib.uv_connect_cb
import io.sunglass.uv.nativelib.utils.lib.uv_os_sock_t

import scala.scalanative.native._
import scala.scalanative.posix.sys.socket.sockaddr

package object tcp {

  @link("uv")
  @extern
  object lib {
    type uv_tcp_t = extern

    def uv_tcp_init(loop: Ptr[uv_loop_t], handle: Ptr[uv_tcp_t]): CInt = extern

    def uv_tcp_init_ex(loop: Ptr[uv_loop_t], handle: Ptr[uv_tcp_t], flags: CUnsignedInt): CInt = extern

    def uv_tcp_open(handle: Ptr[uv_tcp_t], sock: uv_os_sock_t): CInt = extern

    def uv_tcp_nodelay(handle: Ptr[uv_tcp_t], enable: CInt): CInt = extern

    def uv_tcp_keepalive(handle: Ptr[uv_tcp_t], enable: CInt, delay: CUnsignedInt): CInt = extern

    def uv_tcp_simultaneous_accepts(handle: Ptr[uv_tcp_t], enable: CInt): CInt = extern

    def uv_tcp_bind(handle: Ptr[uv_tcp_t],
                    addr: Ptr[sockaddr],
                    flags: UInt): CInt = extern

    def uv_tcp_getsockname(handle: Ptr[uv_tcp_t], name: Ptr[sockaddr], namelen: Ptr[CInt]): CInt = extern

    def uv_tcp_getpeername(handle: Ptr[uv_tcp_t], name: Ptr[sockaddr], namelen: Ptr[CInt]): CInt = extern

    def uv_tcp_connect(req: Ptr[uv_req_t],
                       handle: Ptr[uv_tcp_t],
                       addr: Ptr[sockaddr],
                       cb: uv_connect_cb): CInt = extern
  }

}
