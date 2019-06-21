# 
# The bake file builds protobuf library versioned 3.0.0
# author : derriclyns@gmail.com.com
#

SUMMARY = "Google Protocol Buffer"
DESCRIPTION = "This is protobuf library recipe tailored for speedtest grpc implementation"

# license information
HOMEPAGE = "https://github.com/google/protobuf"
SECTION = "console/tools"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=35953c752efc9299b184f91bef540095"


# build protobuf library first
DEPENDS = "zlib"
PV = "3.0.0+git${SRCPV}"

# version 3.0.0
SRCREV = "e8ae137c96444ea313485ed1118c5e43b2099cf1"

SRC_URI = "git://github.com/google/protobuf.git"
S = "${WORKDIR}/git"

# use c++ compiler
# CXXFLAGS += "-std=c++11"
# BUILD_CXXFLAGS += "-std=c++11"

#inherit auto tools
inherit autotools

PACKAGE_BEFORE_PN = "${PN}-compiler"
FILES_${PN}-compiler = "${bindir} ${libdir}/libprotoc${SOLIBS}"

RDEPENDS_${PN}-compiler = "${PN}"
RDEPENDS_${PN}-dev += "${PN}-compiler"

# extra optimizations
EXTRA_OECONF += " --with-protoc=echo"

# include mips instruction set
MIPS_INSTRUCTION_SET = "mips"


BBCLASSEXTEND = "native nativesdk"
