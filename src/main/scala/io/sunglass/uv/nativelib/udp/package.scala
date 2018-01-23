package io.sunglass.uv.nativelib

import io.sunglass.uv.nativelib.handle.lib.uv_alloc_cb
import io.sunglass.uv.nativelib.loop.lib._
import io.sunglass.uv.nativelib.udp.lib.{uv_membership, uv_udp_flags}
import io.sunglass.uv.nativelib.utils.lib.{uv_buf_t, uv_os_sock_t}

import scala.scalanative.native._
import scala.scalanative.posix.sys.socket.sockaddr

package object udp {
  val UV_UDP_IPV6ONLY: uv_udp_flags = 1.asInstanceOf[uv_udp_flags]
  val UV_UDP_PARTIAL: uv_udp_flags = 2.asInstanceOf[uv_udp_flags]
  val UV_UDP_REUSEADDR: uv_udp_flags = 4.asInstanceOf[uv_udp_flags]
  val UV_LEAVE_GROUP: uv_membership = 0.asInstanceOf[uv_membership]
  val UV_JOIN_GROUP: uv_membership = 1.asInstanceOf[uv_membership]

  @link("uv")
  @extern
  object lib {
    type uv_udp_t = extern
    type uv_udp_send_t = extern
    type uv_udp_flags = extern
    type uv_membership = extern
    type uv_udp_send_cb = CFunctionPtr2[Ptr[uv_udp_t], CInt, Unit]
    type uv_udp_recv_cb = CFunctionPtr5[Ptr[uv_udp_t], CSSize, Ptr[uv_buf_t], Ptr[sockaddr], uv_udp_flags, Unit]

    def uv_udp_init(loop: Ptr[uv_loop_t], handle: Ptr[uv_udp_t]): CInt = extern

    def uv_udp_init_ex(loop: Ptr[uv_loop_t], handle: Ptr[uv_udp_t], flags: CUnsignedInt): CInt = extern

    def uv_udp_open(handle: Ptr[uv_udp_t], sock: uv_os_sock_t): CInt = extern

    def uv_udp_bind(handle: Ptr[uv_udp_t],
                    addr: Ptr[sockaddr],
                    flags: UInt): CInt = extern

    def uv_udp_getsockname(handle: Ptr[uv_udp_t], name: Ptr[sockaddr], namelen: Ptr[CInt]): CInt = extern

    def uv_udp_set_membership(handle: Ptr[uv_udp_t], multicast_addr: CString, interface_addr: CString, membership: uv_membership): CInt = extern

    def uv_udp_set_multicast_loop(handle: Ptr[uv_udp_t], on: CInt): CInt = extern

    def uv_udp_set_multicast_ttl(handle: Ptr[uv_udp_t], ttl: CInt): CInt = extern

    def uv_udp_set_multicast_interface(handle: Ptr[uv_udp_t], interface_addr: CString): CInt = extern

    def uv_udp_set_broadcast(handle: Ptr[uv_udp_t], on: CInt): CInt = extern

    def uv_udp_set_ttl(handle: Ptr[uv_udp_t], ttl: CInt): CInt = extern

    def uv_udp_send(req: Ptr[uv_udp_send_t],
                    handle: Ptr[uv_udp_t],
                    bufs: Ptr[uv_buf_t],
                    nbufs: CUnsignedInt,
                    addr: Ptr[sockaddr],
                    send_cb: uv_udp_send_cb): CInt = extern

    def uv_udp_try_send(handle: Ptr[uv_udp_t],
                        bufs: Ptr[uv_buf_t],
                        nbufs: CUnsignedInt,
                        addr: Ptr[sockaddr]): CInt = extern

    def uv_udp_recv_start(handle: Ptr[uv_udp_t], alloc_cb: uv_alloc_cb, recv_cb: uv_udp_recv_cb): CInt = extern

    def uv_udp_recv_stop(handle: Ptr[uv_udp_t]): CInt = extern

    def uv_udp_get_send_queue_size(handle: Ptr[uv_udp_t]): CSize = extern

    def uv_udp_get_send_queue_count(handle: Ptr[uv_udp_t]): CSize = extern
  }

}
