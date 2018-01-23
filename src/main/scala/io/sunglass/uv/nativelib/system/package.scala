package io.sunglass.uv.nativelib

import scala.scalanative.native._

package object system {

  @link("sunglass0")
  @extern
  object lib {
    @name("sunglass_sig_kill")
    def SIGKILL: CInt = extern

    @name("sunglass_sig_usr1")
    def SIGUSR1: CInt = extern

    @name("sunglass_sig_usr2")
    def SIGUSR2: CInt = extern

  }

}
