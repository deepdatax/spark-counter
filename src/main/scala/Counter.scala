import org.apache.spark.sql.SparkSession

object Counter {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .appName("Counter")
      .getOrCreate()

    val df = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", "test")
      .load()

    df.writeStream
      .format("console")
      .option("truncate","false")
      .start()
      .awaitTermination()
  }
}
