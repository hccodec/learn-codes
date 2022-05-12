# file: setup.py
from distutils.core import setup, Extension
from Cython.Build import cythonize

extension = Extension(
    "queue",
    ["queue.pyx"],
    libraries=["cqueue"]  # 在这边声明需要链接的C库（libcqueue.so）
)

setup(
    ext_modules=cythonize([extension])
)