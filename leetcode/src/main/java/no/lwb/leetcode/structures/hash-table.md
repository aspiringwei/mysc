hash-table
--
using hash functions in order to support quick insertion and search.

key ideas
--
use a hash function to map keys to buckets
![](https://s3-lc-upload.s3.amazonaws.com/uploads/2018/02/20/screen-shot-2018-02-19-at-183537.png)
1. 通过hash函数决定key分配到哪个桶中
2. 通过hash函数查找相应的的桶并在特定桶搜索


Collision hash碰撞
--
一个桶多个key的情况