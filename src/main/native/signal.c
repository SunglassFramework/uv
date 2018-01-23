#include <stdlib.h>
#include <signal.h>
#include "uv.h"

__attribute__ ((visibility ("default")))

size_t sunglass_uv_signal_size() {
    return sizeof(uv_signal_t);
}

int sunglass_sig_hup() {
    return SIGHUP;
}

int sunglass_sig_kill() {
    return SIGKILL;
}
int sunglass_sig_quit() {
    return SIGQUIT;
}
int sunglass_sig_usr1() {
    return SIGUSR1;
}
int sunglass_sig_usr2() {
    return SIGUSR2;
}