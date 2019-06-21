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
# The bake file builds protoc aswell as protobuf-c library versioned 1.3.1 
# author : derric.lyns@gmail.com
#

SUMMARY = "Google Protocol Buffer C wrapper" 
DESCRIPTION = "This is protobuf-c, a C wrapper for the Google Protocol Buffers"

# license information
HOMEPAGE = "https://github.com/protobuf-c/protobuf-c" 
SECTION = "console/tools" 
LICENSE = "BSD-2-Clause" 
LIC_FILES_CHKSUM = "file://LICENSE;md5=cb901168715f4782a2b06c3ddaefa558"

# build protobuf library first
DEPENDS = "speedtest-protobuf" 
PV .= "+git${SRCPV}"

# the version is v1.3.1
SRCREV = "269771b4b45d3aba04e59569f53600003db8d9ff" 
SRC_URI = "git://github.com/protobuf-c/protobuf-c.git" 
S = "${WORKDIR}/git"

# use c++ compiler
CXXFLAGS += "-std=c++11" 
BUILD_CXXFLAGS += "-std=c++11"

#inherit auto tools
inherit autotools pkgconfig 

PACKAGE_BEFORE_PN = "${PN}-compiler"
FILES_${PN}-compiler = "${bindir}"
RDEPENDS_${PN}-compiler = "protobuf-compiler"
RDEPENDS_${PN}-dev += "${PN}-compiler"

BBCLASSEXTEND = "native nativesdk"

# disable protoc library and compiler build
EXTRA_OECONF += " --disable-protoc"