#
# Copyright 2019, skynetcore
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
# 
# The bake file builds grpc library versioned 1.21.3 
# this recipe provides libgrpc++, libgrpc, libgpr and libaddress_sorting libraries
# there is a dependency on google protocol buffer versioned 3.7.0
# author : derric.lyns@gmail.com
#

DESCRIPTION = "An RPC library and framework using google protocol buffers"
AUTHOR = "Google Inc."

# license information
HOMEPAGE = "http://www.grpc.io"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

inherit cmake

# depends on gflags, protobuf, openssl, zlin and c-ares
DEPENDS = "\
    speedtest-gflags \
	speedtest-protobuf \
    zlib \
    openssl \
    c-ares \
     "

# fork from google versioned 1.21.3
SRC_URI = "gitsm://github.com/skynetcore/grpc.git;branch=embedded_1.21.x"
SRCREV = "d06ebf34f6ea2d461e7678b188704e20fe25bf4a"

PR = "r1"
S = "${WORKDIR}/git"

# allow atomic allocations
DEBUG_BUILD = "1"

# Fix static library generation
AR += "rcs"
OUTDIR = "${S}/libs/opt/"
TARGET_CFLAGS += "-Wno-error"

# build settings
EXTRA_OECMAKE = " \
    -DgRPC_CARES_PROVIDER=module \
    -DgRPC_ZLIB_PROVIDER=package \
    -DgRPC_SSL_PROVIDER=package \
    -DgRPC_PROTOBUF_PROVIDER=module \
    -DgRPC_GFLAGS_PROVIDER=package \
    -DgRPC_INSTALL=ON \
    -DCMAKE_CROSSCOMPILING=ON \
    -DBUILD_SHARED_LIBS=ON \
    "

# install libraries and headers
do_install () {
    install -d ${D}${libdir}
    install -m 0755 ${S}/../build/libgpr.so ${D}${libdir}/libgpr.so
    install -m 0755 ${S}/../build/libgrpc.so ${D}${libdir}/libgrpc.so
    install -m 0755 ${S}/../build/libgrpc++.so ${D}${libdir}/libgrpc++.so
    install -m 0755 ${S}/../build/libaddress_sorting.so ${D}${libdir}/libaddress_sorting.so
    install -m 0755 ${S}/../build/third_party/protobuf/libprotobuf.so.3.7.0.0 ${D}${libdir}/libprotobuf.so.3.7.0.0
    install -d ${D}${includedir}
    cp -r ${S}/../git/include/* ${D}${includedir}/.
}