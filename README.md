# srt-reader-java
Srt字幕文件的解析，支持多行解析，支持播放器是时间轴对应，读取的时间复杂度低


----
# Srt字幕文件的解析
* 支持所有的文件格式的解析
* 支持Srt多行字幕的解析
* 加载字幕方式：通过TreeMap的数据结构去读取字幕文件，时间复杂度O（1）
* 可以结合播放器，去读取每一秒对应的字幕

# 包结构
* Main.java 为测试的调用类
* PlayerSubTitleProxy 为代理类，注册一个监听器，如果加载成功则为字幕文件正确
* TuziSubTitleInfoTreeMap 为核心数据结构
* SRTReader 为读取类，用于去读取字幕文件




