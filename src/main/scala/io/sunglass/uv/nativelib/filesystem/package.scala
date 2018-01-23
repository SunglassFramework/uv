
package io.sunglass.uv.nativelib


import io.sunglass.uv.nativelib.filesystem.lib.{uv_dirent_type_t, uv_fs_type}
import io.sunglass.uv.nativelib.loop.lib.uv_loop_t
import io.sunglass.uv.nativelib.utils.lib.{uv_buf_t, uv_file, uv_os_fd_t}

import scala.scalanative.native._

package object filesystem {
  val UV_FS_UNKNOWN: uv_fs_type = (-1).asInstanceOf[uv_fs_type]
  val UV_FS_CUSTOM: uv_fs_type = 0.asInstanceOf[uv_fs_type]
  val UV_FS_OPEN: uv_fs_type = 1.asInstanceOf[uv_fs_type]
  val UV_FS_CLOSE: uv_fs_type = 2.asInstanceOf[uv_fs_type]
  val UV_FS_READ: uv_fs_type = 3.asInstanceOf[uv_fs_type]
  val UV_FS_WRITE: uv_fs_type = 4.asInstanceOf[uv_fs_type]
  val UV_FS_SENDFILE: uv_fs_type = 5.asInstanceOf[uv_fs_type]
  val UV_FS_STAT: uv_fs_type = 6.asInstanceOf[uv_fs_type]
  val UV_FS_LSTAT: uv_fs_type = 7.asInstanceOf[uv_fs_type]
  val UV_FS_FSTAT: uv_fs_type = 8.asInstanceOf[uv_fs_type]
  val UV_FS_FTRUNCATE: uv_fs_type = 9.asInstanceOf[uv_fs_type]
  val UV_FS_UTIME: uv_fs_type = 10.asInstanceOf[uv_fs_type]
  val UV_FS_FUTIME: uv_fs_type = 11.asInstanceOf[uv_fs_type]
  val UV_FS_ACCESS: uv_fs_type = 12.asInstanceOf[uv_fs_type]
  val UV_FS_CHMOD: uv_fs_type = 13.asInstanceOf[uv_fs_type]
  val UV_FS_FCHMOD: uv_fs_type = 14.asInstanceOf[uv_fs_type]
  val UV_FS_FSYNC: uv_fs_type = 15.asInstanceOf[uv_fs_type]
  val UV_FS_FDATASYNC: uv_fs_type = 16.asInstanceOf[uv_fs_type]
  val UV_FS_UNLINK: uv_fs_type = 17.asInstanceOf[uv_fs_type]
  val UV_FS_RMDIR: uv_fs_type = 18.asInstanceOf[uv_fs_type]
  val UV_FS_MKDIR: uv_fs_type = 19.asInstanceOf[uv_fs_type]
  val UV_FS_MKDTEMP: uv_fs_type = 20.asInstanceOf[uv_fs_type]
  val UV_FS_RENAME: uv_fs_type = 21.asInstanceOf[uv_fs_type]
  val UV_FS_SCANDIR: uv_fs_type = 22.asInstanceOf[uv_fs_type]
  val UV_FS_LINK: uv_fs_type = 23.asInstanceOf[uv_fs_type]
  val UV_FS_SYMLINK: uv_fs_type = 24.asInstanceOf[uv_fs_type]
  val UV_FS_READLINK: uv_fs_type = 25.asInstanceOf[uv_fs_type]
  val UV_FS_CHOWN: uv_fs_type = 26.asInstanceOf[uv_fs_type]
  val UV_FS_FCHOWN: uv_fs_type = 27.asInstanceOf[uv_fs_type]
  val UV_FS_REALPATH: uv_fs_type = 28.asInstanceOf[uv_fs_type]
  val UV_FS_COPYFIL: uv_fs_type = 29.asInstanceOf[uv_fs_type]

  val UV_DIRENT_UNKNOWN: uv_dirent_type_t = 0.asInstanceOf[uv_dirent_type_t]
  val UV_DIRENT_FILE: uv_dirent_type_t = 1.asInstanceOf[uv_dirent_type_t]
  val UV_DIRENT_DIR: uv_dirent_type_t = 2.asInstanceOf[uv_dirent_type_t]
  val UV_DIRENT_LINK: uv_dirent_type_t = 3.asInstanceOf[uv_dirent_type_t]
  val UV_DIRENT_FIFO: uv_dirent_type_t = 4.asInstanceOf[uv_dirent_type_t]
  val UV_DIRENT_SOCKET: uv_dirent_type_t = 5.asInstanceOf[uv_dirent_type_t]
  val UV_DIRENT_CHAR: uv_dirent_type_t = 6.asInstanceOf[uv_dirent_type_t]
  val UV_DIRENT_BLOCK: uv_dirent_type_t = 7.asInstanceOf[uv_dirent_type_t]

  @link("uv")
  @extern
  object lib {
    type uv_uid_t = extern
    type uv_gid_t = extern
    type uv_fs_t = extern
    type uv_timespec_t = extern
    type uv_stat_t = extern
    type uv_fs_type = extern
    type uv_dirent_t = extern
    type uv_dirent_type_t = extern
    type uv_fs_cb = CFunctionPtr1[Ptr[uv_fs_t], Unit]

    def uv_fs_req_cleanup(req: Ptr[uv_fs_t]): Unit = extern

    def uv_fs_close(loop: Ptr[uv_loop_t], req: Ptr[uv_fs_t], file: uv_file, cb: uv_fs_cb): CInt = extern

    def uv_fs_open(loop: Ptr[uv_loop_t], req: Ptr[uv_fs_t], path: CString, flags: CInt, mode: CInt, cb: uv_fs_cb): CInt = extern

    def uv_fs_read(loop: Ptr[uv_loop_t], req: Ptr[uv_fs_t],
                   file: uv_file,
                   bufs: Ptr[uv_buf_t],
                   nbufs: CUnsignedInt,
                   offset: CLongLong,
                   cb: uv_fs_cb): CInt = extern

    def uv_fs_unlink(loop: Ptr[uv_loop_t], req: Ptr[uv_fs_t], path: CString, cb: uv_fs_cb): CInt = extern

    def uv_fs_write(loop: Ptr[uv_loop_t], req: Ptr[uv_fs_t],
                    file: uv_file,
                    bufs: Ptr[uv_buf_t],
                    nbufs: CUnsignedInt,
                    offset: CLongLong,
                    cb: uv_fs_cb): CInt = extern

    def uv_fs_mkdir(loop: Ptr[uv_loop_t], req: Ptr[uv_fs_t], path: CString, mode: CInt, cb: uv_fs_cb): CInt = extern

    def uv_fs_mkdtemp(loop: Ptr[uv_loop_t], req: Ptr[uv_fs_t], tpl: CString, cb: uv_fs_cb): CInt = extern

    def uv_fs_rmdir(loop: Ptr[uv_loop_t], req: Ptr[uv_fs_t], path: CString, cb: uv_fs_cb): CInt = extern

    def uv_fs_scandir(loop: Ptr[uv_loop_t], req: Ptr[uv_fs_t], path: CString, flags: CInt, cb: uv_fs_cb): CInt = extern

    /**
      * Equivalent to scandir(3), with a slightly different API. Once the callback for the request is called,
      * the user can use [[uv_fs_scandir_next()]] to get ent populated with the next directory entry data.
      * When there are no more entries UV_EOF will be returned.
      *
      * *
      * Unlike scandir(3), this function does not return the “.” and “..” entries.
      *
      * @param req
      * @param ent
      * @return
      */
    def uv_fs_scandir_next(req: Ptr[uv_fs_t], ent: Ptr[uv_dirent_t]): CInt = extern

    def uv_fs_stat(loop: Ptr[uv_loop_t], req: Ptr[uv_fs_t], path: CString, cb: uv_fs_cb): CInt = extern

    def uv_fs_fstat(loop: Ptr[uv_loop_t], req: Ptr[uv_fs_t], file: uv_file, cb: uv_fs_cb): CInt = extern

    def uv_fs_lstat(loop: Ptr[uv_loop_t], req: Ptr[uv_fs_t], path: CString, cb: uv_fs_cb): CInt = extern

    def uv_fs_rename(loop: Ptr[uv_loop_t],
                     req: Ptr[uv_fs_t],
                     path: CString,
                     new_path: CString,
                     cb: uv_fs_cb): CInt = extern

    def uv_fs_fsync(loop: Ptr[uv_loop_t], req: Ptr[uv_fs_t], file: uv_file, cb: uv_fs_cb): CInt = extern

    def uv_fs_fdatasync(loop: Ptr[uv_loop_t], req: Ptr[uv_fs_t], file: uv_file, cb: uv_fs_cb): CInt = extern

    def uv_fs_ftruncate(loop: Ptr[uv_loop_t],
                        req: Ptr[uv_fs_t],
                        file: uv_file,
                        offset: CLongLong, cb: uv_fs_cb): CInt = extern

    def uv_fs_copy(loop: Ptr[uv_loop_t],
                   req: Ptr[uv_fs_t],
                   path: CString,
                   new_path: CString,
                   flags: CInt,
                   cb: uv_fs_cb): CInt = extern

    def uv_fs_sendfile(loop: Ptr[uv_loop_t],
                       req: Ptr[uv_fs_t],
                       out_fd: uv_file,
                       in_fd: uv_file,
                       in_offset: CLongLong,
                       length: CSize,
                       cb: uv_fs_cb): CInt = extern

    def uv_fs_access(loop: Ptr[uv_loop_t],
                     req: Ptr[uv_fs_t],
                     path: CString,
                     mode: CInt,
                     cb: uv_fs_cb): CInt = extern

    def uv_fs_chmod(loop: Ptr[uv_loop_t],
                    req: Ptr[uv_fs_t],
                    path: CString,
                    mode: CInt,
                    cb: uv_fs_cb): CInt = extern

    def uv_fs_fchmod(loop: Ptr[uv_loop_t],
                     req: Ptr[uv_fs_t],
                     file: uv_file,
                     mode: CInt,
                     cb: uv_fs_cb): CInt = extern

    def uv_fs_utime(loop: Ptr[uv_loop_t],
                    req: Ptr[uv_fs_t],
                    path: CString,
                    atime: CDouble,
                    mtime: CDouble,
                    cb: uv_fs_cb): CInt = extern

    def uv_fs_futime(loop: Ptr[uv_loop_t],
                     req: Ptr[uv_fs_t],
                     file: uv_file,
                     atime: CDouble,
                     mtime: CDouble,
                     cb: uv_fs_cb): CInt = extern

    def uv_fs_link(loop: Ptr[uv_loop_t],
                   req: Ptr[uv_fs_t],
                   path: CString,
                   new_path: CString,
                   cb: uv_fs_cb): CInt = extern

    def uv_fs_symlink(loop: Ptr[uv_loop_t],
                      req: Ptr[uv_fs_t],
                      path: CString,
                      new_path: CString,
                      flags: CInt,
                      cb: uv_fs_cb): CInt = extern

    def uv_fs_readlink(loop: Ptr[uv_loop_t],
                       req: Ptr[uv_fs_t],
                       path: CString,
                       cb: uv_fs_cb): CInt = extern

    def uv_fs_readpath(loop: Ptr[uv_loop_t],
                       req: Ptr[uv_fs_t],
                       path: CString,
                       cb: uv_fs_cb): CInt = extern

    def uv_fs_chown(loop: Ptr[uv_loop_t],
                    req: Ptr[uv_fs_t],
                    path: CString,
                    uid: uv_uid_t,
                    gid: uv_gid_t,
                    cb: uv_fs_cb): CInt = extern

    def uv_fs_fchown(loop: Ptr[uv_loop_t],
                     req: Ptr[uv_fs_t],
                     file: uv_file,
                     uid: uv_uid_t,
                     gid: uv_gid_t,
                     cb: uv_fs_cb): CInt = extern

    def uv_fs_get_type(req: Ptr[uv_fs_t]): uv_fs_type = extern

    def uv_fs_get_result(req: Ptr[uv_fs_t]): CSSize = extern

    def uv_fs_get_ptr(req: Ptr[uv_fs_t]): Ptr[Byte] = extern

    def uv_fs_get_path(req: Ptr[uv_fs_t]): CString = extern

    def uv_fs_get_statbbuf(req: Ptr[uv_fs_t]): Ptr[uv_stat_t] = extern

    def uv_get_osfhandle(fd: CInt): uv_os_fd_t = extern

  }

}
