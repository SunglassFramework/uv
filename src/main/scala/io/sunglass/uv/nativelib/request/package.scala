package io.sunglass.uv.nativelib


import io.sunglass.uv.nativelib.request.lib.uv_req_type

import scala.scalanative.native._

package object request {

  val UV_UNKNOWN_REQ: uv_req_type = 0.asInstanceOf[uv_req_type]
  val UV_REQ: uv_req_type = 1.asInstanceOf[uv_req_type]
  val UV_CONNECT: uv_req_type = 2.asInstanceOf[uv_req_type]
  val UV_WRITE: uv_req_type = 3.asInstanceOf[uv_req_type]
  val UV_SHUTDOWN: uv_req_type = 4.asInstanceOf[uv_req_type]
  val UV_UDP_SEND: uv_req_type = 5.asInstanceOf[uv_req_type]
  val UV_FS: uv_req_type = 6.asInstanceOf[uv_req_type]
  val UV_WORK: uv_req_type = 7.asInstanceOf[uv_req_type]
  val UV_GETADDRINFO: uv_req_type = 8.asInstanceOf[uv_req_type]
  val UV_GETNAMEINFO: uv_req_type = 9.asInstanceOf[uv_req_type]
  val UV_REQ_TYPE_PRIVATE: uv_req_type = 10.asInstanceOf[uv_req_type]
  val UV_REQ_TYPE_MAX: uv_req_type = 11.asInstanceOf[uv_req_type]

  @link("uv")
  @extern
  object lib {


    type uv_req_t = extern
    type uv_req_type = extern


    def uv_cancel(req: Ptr[uv_req_t]): CInt = extern

    def uv_req_size(`type`: uv_req_type): CSize = extern

    def uv_req_get_data(req: Ptr[uv_req_t]): Ptr[Byte] = extern

    def uv_req_set_data(req: Ptr[uv_req_t], data: Ptr[Byte]): Ptr[Byte] = extern

    def uv_req_get_type(req: Ptr[uv_req_t]): uv_req_type = extern


    def uv_req_type_name(`type`: uv_req_type): CString = extern

  }
}
