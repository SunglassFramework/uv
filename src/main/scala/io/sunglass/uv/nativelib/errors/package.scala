package io.sunglass.uv.nativelib

import scala.scalanative.native._

package object errors {

  @link("uv")
  @extern
  object lib {
    def uv_strerror(err: CInt): CString = extern

    def uv_err_name(err: CInt): CString = extern

    def uv_translate_sys_error(err: CInt): CInt = extern
  }

  @link("sunglass0")
  @extern
  object extra {
    @name("sunglass_UV_E2BIG")
    def UV_E2BIG: CInt = extern

    @name("sunglass_UV_EACCES")
    def UV_EACCES: CInt = extern

    @name("sunglass_UV_EADDRINUSE")
    def UV_EADDRINUSE: CInt = extern

    @name("sunglass_UV_EADDRNOTAVAIL")
    def UV_EADDRNOTAVAIL: CInt = extern

    @name("sunglass_UV_EAFNOSUPPORT")
    def UV_EAFNOSUPPORT: CInt = extern

    @name("sunglass_UV_EAGAIN")
    def UV_EAGAIN: CInt = extern

    @name("sunglass_UV_EAI_ADDRFAMILY")
    def UV_EAI_ADDRFAMILY: CInt = extern

    @name("sunglass_UV_EAI_AGAIN")
    def UV_EAI_AGAIN: CInt = extern

    @name("sunglass_UV_EAI_BADFLAGS")
    def UV_EAI_BADFLAGS: CInt = extern

    @name("sunglass_UV_EAI_BADHINTS")
    def UV_EAI_BADHINTS: CInt = extern

    @name("sunglass_UV_EAI_CANCELED")
    def UV_EAI_CANCELED: CInt = extern

    @name("sunglass_UV_EAI_FAIL")
    def UV_EAI_FAIL: CInt = extern

    @name("sunglass_UV_EAI_FAMILY")
    def UV_EAI_FAMILY: CInt = extern

    @name("sunglass_UV_EAI_MEMORY")
    def UV_EAI_MEMORY: CInt = extern

    @name("sunglass_UV_EAI_NODATA")
    def UV_EAI_NODATA: CInt = extern

    @name("sunglass_UV_EAI_NONAME")
    def UV_EAI_NONAME: CInt = extern

    @name("sunglass_UV_EAI_OVERFLOW")
    def UV_EAI_OVERFLOW: CInt = extern

    @name("sunglass_UV_EAI_PROTOCOL")
    def UV_EAI_PROTOCOL: CInt = extern

    @name("sunglass_UV_EAI_SERVICE")
    def UV_EAI_SERVICE: CInt = extern

    @name("sunglass_UV_EAI_SOCKTYPE")
    def UV_EAI_SOCKTYPE: CInt = extern

    @name("sunglass_UV_EALREADY")
    def UV_EALREADY: CInt = extern

    @name("sunglass_UV_EBADF")
    def UV_EBADF: CInt = extern

    @name("sunglass_UV_EBUSY")
    def UV_EBUSY: CInt = extern

    @name("sunglass_UV_ECANCELED")
    def UV_ECANCELED: CInt = extern

    @name("sunglass_UV_ECHARSET")
    def UV_ECHARSET: CInt = extern

    @name("sunglass_UV_ECONNABORTED")
    def UV_ECONNABORTED: CInt = extern

    @name("sunglass_UV_ECONNREFUSED")
    def UV_ECONNREFUSED: CInt = extern

    @name("sunglass_UV_ECONNRESET")
    def UV_ECONNRESET: CInt = extern

    @name("sunglass_UV_EDESTADDRREQ")
    def UV_EDESTADDRREQ: CInt = extern

    @name("sunglass_UV_EEXIST")
    def UV_EEXIST: CInt = extern

    @name("sunglass_UV_EFAULT")
    def UV_EFAULT: CInt = extern

    @name("sunglass_UV_EFBIG")
    def UV_EFBIG: CInt = extern

    @name("sunglass_UV_EHOSTUNREACH")
    def UV_EHOSTUNREACH: CInt = extern

    @name("sunglass_UV_EINTR")
    def UV_EINTR: CInt = extern

    @name("sunglass_UV_EINVAL")
    def UV_EINVAL: CInt = extern

    @name("sunglass_UV_EIO")
    def UV_EIO: CInt = extern

    @name("sunglass_UV_EISCONN")
    def UV_EISCONN: CInt = extern

    @name("sunglass_UV_EISDIR")
    def UV_EISDIR: CInt = extern

    @name("sunglass_UV_ELOOP")
    def UV_ELOOP: CInt = extern

    @name("sunglass_UV_EMFILE")
    def UV_EMFILE: CInt = extern

    @name("sunglass_UV_EMSGSIZE")
    def UV_EMSGSIZE: CInt = extern

    @name("sunglass_UV_ENAMETOOLONG")
    def UV_ENAMETOOLONG: CInt = extern

    @name("sunglass_UV_ENETDOWN")
    def UV_ENETDOWN: CInt = extern

    @name("sunglass_UV_ENETUNREACH")
    def UV_ENETUNREACH: CInt = extern

    @name("sunglass_UV_ENFILE")
    def UV_ENFILE: CInt = extern

    @name("sunglass_UV_ENOBUFS")
    def UV_ENOBUFS: CInt = extern

    @name("sunglass_UV_ENODEV")
    def UV_ENODEV: CInt = extern

    @name("sunglass_UV_ENOENT")
    def UV_ENOENT: CInt = extern

    @name("sunglass_UV_ENOMEM")
    def UV_ENOMEM: CInt = extern

    @name("sunglass_UV_ENONET")
    def UV_ENONET: CInt = extern

    @name("sunglass_UV_ENOPROTOOPT")
    def UV_ENOPROTOOPT: CInt = extern

    @name("sunglass_UV_ENOSPC")
    def UV_ENOSPC: CInt = extern

    @name("sunglass_UV_ENOSYS")
    def UV_ENOSYS: CInt = extern

    @name("sunglass_UV_ENOTCONN")
    def UV_ENOTCONN: CInt = extern

    @name("sunglass_UV_ENOTDIR")
    def UV_ENOTDIR: CInt = extern

    @name("sunglass_UV_ENOTEMPTY")
    def UV_ENOTEMPTY: CInt = extern

    @name("sunglass_UV_ENOTSOCK")
    def UV_ENOTSOCK: CInt = extern

    @name("sunglass_UV_ENOTSUP")
    def UV_ENOTSUP: CInt = extern

    @name("sunglass_UV_EPERM")
    def UV_EPERM: CInt = extern

    @name("sunglass_UV_EPIPE")
    def UV_EPIPE: CInt = extern

    @name("sunglass_UV_EPROTO")
    def UV_EPROTO: CInt = extern

    @name("sunglass_UV_EPROTONOSUPPORT")
    def UV_EPROTONOSUPPORT: CInt = extern

    @name("sunglass_UV_EPROTOTYPE")
    def UV_EPROTOTYPE: CInt = extern

    @name("sunglass_UV_ERANGE")
    def UV_ERANGE: CInt = extern

    @name("sunglass_UV_EROFS")
    def UV_EROFS: CInt = extern

    @name("sunglass_UV_ESHUTDOWN")
    def UV_ESHUTDOWN: CInt = extern

    @name("sunglass_UV_ESPIPE")
    def UV_ESPIPE: CInt = extern

    @name("sunglass_UV_ESRCH")
    def UV_ESRCH: CInt = extern

    @name("sunglass_UV_ETIMEDOUT")
    def UV_ETIMEDOUT: CInt = extern

    @name("sunglass_UV_ETXTBSY")
    def UV_ETXTBSY: CInt = extern

    @name("sunglass_UV_EXDEV")
    def UV_EXDEV: CInt = extern

    @name("sunglass_UV_UNKNOWN")
    def UV_UNKNOWN: CInt = extern

    @name("sunglass_UV_EOF")
    def UV_EOF: CInt = extern

    @name("sunglass_UV_ENXIO")
    def UV_ENXIO: CInt = extern

    @name("sunglass_UV_EMLINK")
    def UV_EMLINK: CInt = extern
  }

}
