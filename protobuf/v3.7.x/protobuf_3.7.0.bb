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
# The bake file builds protobuf and protobuf-c library versioned 3.7.0
# author : derric.lyns@gmail.com
#

SUMMARY = "Protocol Buffers - structured data serialisation mechanism"
DESCRIPTION = "Protocol Buffers are a way of encoding structured data in an \
efficient yet extensible format. Google uses Protocol Buffers for almost \
all of its internal RPC protocols and file formats."

# license information
HOMEPAGE = "https://github.com/google/protobuf"
SECTION = "console/tools"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=37b5762e07f0af8c74ce80a8bda4266b"

# depends on zlib
DEPENDS = "zlib"

PV .= "+git${SRCPV}"

# the version is v3.7.0
SRCREV = "9f6959c65a34a4f6da67e959b81df0811ed57979"
SRC_URI = "git://github.com/skynetcore/protobuf.git;branch=embedded_3.7.0"
S = "${WORKDIR}/git"

inherit cmake

# files
PACKAGE_BEFORE_PN = "${PN}-compiler ${PN}-lite"
FILES_${PN}-compiler = "${bindir} ${libdir}/libprotoc${SOLIBS}"
FILES_${PN}-lite = "${libdir}/libprotobuf-lite${SOLIBS}"
RDEPENDS_${PN}-compiler = "${PN}"
RDEPENDS_${PN}-dev += "${PN}-compiler"
RDEPENDS_${PN}-ptest = "bash ${@bb.utils.contains('PACKAGECONFIG', 'python', 'python-protobuf', '', d)}"

# compiler flags
MIPS_INSTRUCTION_SET = "mips"
BBCLASSEXTEND = "native nativesdk"

LDFLAGS_append_arm = " -latomic"
LDFLAGS_append_mips = " -latomic"
LDFLAGS_append_powerpc = " -latomic"
LDFLAGS_append_mipsel = " -latomic"

# build shared libraries only
EXTRA_OECMAKE += " -DBUILD_SHARED_LIBS=1"
