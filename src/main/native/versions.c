#include <stdlib.h>
#include <signal.h>
#include "uv.h"

__attribute__ ((visibility ("default")))

int sunglass_UV_VERSION_MAJOR() { return UV_VERSION_MAJOR; }
int sunglass_UV_VERSION_MINOR() { return UV_VERSION_MINOR; }
int sunglass_UV_VERSION_PATCH() { return UV_VERSION_PATCH; }
int sunglass_UV_VERSION_IS_RELEASE() { return UV_VERSION_IS_RELEASE; }