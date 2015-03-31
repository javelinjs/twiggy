package me.yzhi.twiggy.util

/**
 */
object FileUtils {
  def readFileToString(filename: String, output: String): Boolean = {
    /* TODO
    File* file = File::open(file_name, "r");
    if (file == NULL) return false;
    size_t size = file->size();
    return (size <= file->readToString(output, size*100));
    */
    true
  }
}
