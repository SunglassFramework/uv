package io.sunglass.uv.nativelib

import io.sunglass.uv.nativelib.loop.lib.uv_loop_t

import scala.scalanative.native._
import scala.scalanative.posix.netdb._
import scala.scalanative.posix.sys.socket.sockaddr

package object dns {

  @link("uv")
  @extern
  object lib {
    type uv_getaddrinfo_t = extern
    type uv_getaddrinfo_cb = CFunctionPtr3[Ptr[uv_getaddrinfo_t], CInt, Ptr[addrinfo], Unit]
    type uv_getnameinfo_t = extern
    type uv_getnameinfo_cb = CFunctionPtr4[Ptr[uv_getnameinfo_t], CInt, CString, CString, Unit]

    /**
      * Asynchronous getaddrinfo(3).
      * *
      * Either node or service may be NULL but not both.
      * *
      * hints is a pointer to a struct addrinfo with additional address type constraints, or NULL. Consult man -s 3 getaddrinfo for more details.
      * *
      * Returns 0 on success or an error code < 0 on failure. If successful, the callback will get called sometime in the future with the lookup result, which is either:
      * *
      * status == 0, the res argument points to a valid struct addrinfo, or
      * status < 0, the res argument is NULL. See the UV_EAI_* constants.
      * *
      * Call [[uv_freeaddrinfo()]] to free the addrinfo structure.
      * Changed in version 1.3.0: the callback parameter is now allowed to be NULL, in which case the request will run synchronously.
      *
      * @param loop
      * @param req
      * @param getaddrinfo_cb
      * @param node
      * @param service
      * @param hints
      * @return 0 on success or an error code < 0 on failure. If successful, the callback will get called sometime in the future with the lookup result, which is either:
      *         *
      *         status == 0, the res argument points to a valid struct addrinfo, or
      *         status < 0, the res argument is NULL. See the UV_EAI_* constants.
      */
    def uv_getaddrinfo(loop: Ptr[uv_loop_t],
                       req: Ptr[uv_getaddrinfo_t],
                       getaddrinfo_cb: uv_getaddrinfo_cb,
                       node: CString,
                       service: CString,
                       hints: Ptr[addrinfo]
                      ): CInt = extern

    /**
      * Free the struct addrinfo. Passing NULL is allowed and is a no-op.
      *
      * @param ai
      */
    def uv_freeaddrinfo(ai: Ptr[addrinfo]): Unit = extern

    /**
      * *
      * Asynchronous getnameinfo(3).
      * *
      * Returns 0 on success or an error code < 0 on failure.
      * If successful, the callback will get called sometime in the future with the lookup result. Consult man -s 3 getnameinfo for more details.
      * *
      * Changed in version 1.3.0: the callback parameter is now allowed to be NULL, in which case the request will run synchronously.
      *
      **/
    def uv_getnameinfo(loop: Ptr[uv_loop_t],
                       req: Ptr[uv_getnameinfo_t],
                       getnameinfo_cb: uv_getnameinfo_cb,
                       addr: Ptr[sockaddr],
                       flags: CInt): CInt = extern

  }

}
