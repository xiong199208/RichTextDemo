# RichTextDemo
富文本的使用和进阶

###富文本的核心，就是让TextView支持HTML格式文本，复合文本，超链接

#### 1.HTML格式文本


显示效果如下：
![这里写图片描述](http://img.blog.csdn.net/20161103232503708)


###2.富文本的各种标签的使用


![这里写图片描述](http://img.blog.csdn.net/20161104141912967)

### 3.使用复合文本，实现图文混排


![这里写图片描述](http://img.blog.csdn.net/20161104203148395)	

###4.实现TextView里的超链接：

* 跳转到网页的超链接
* 隐式意图，如打电话的超链接
* 自定义的超链接


![这里写图片描述](http://img.blog.csdn.net/20161104225526021)
 

###5.富文本的替换规则
 


![这里写图片描述](http://img.blog.csdn.net/20161104232215635)

<big><b>其实这里的Flag就是一种替换规则：
 * Spanned.SPAN_INCLUSIVE_EXCLUSIVE ===》包前不包后
 * Spanned.SPAN_EXCLUSIVE_INCLUSIVE====》包后不包前
 * Spanned.SPAN_INCLUSIVE_INCLUSIVE====》前后都包
 * Spanned.SPAN_EXCLUSIVE_EXCLUSIVE=====》前后都不包</b></big>

