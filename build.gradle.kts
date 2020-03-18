plugins {
    application
    kotlin("jvm") version "1.3.10"
    java
}

application {
    mainClassName = "Main"
}
dependencies {
    // 依赖管理，完美替代eclipse中的导包和buildPath

    implementation(kotlin("stdlib"))
//    compile group: 'org.apache.httpcomponents', name: 'httpclient', version: '4.5'
//    compile group: 'commons-httpclient', name: 'commons-httpclient', version: '3.1'
    implementation("commons-httpclient", "commons-httpclient", "3.1")

}

repositories {
    google()
    maven { setUrl("http://maven.aliyun.com/nexus/content/groups/public/") }
    maven { setUrl("http://maven.aliyun.com/nexus/content/repositories/jcenter") }
    // maven仓库，上面的依赖的某个库都在这边的仓库里
    mavenCentral()
    jcenter()
}

// gradle的task在运行时，会先扫描左右内容，再执行对应的task
task("helloworld") {
    println("hello ddsds")
}
task("woshou") {
    var s = "dddd"// 扫描时就会执行
    // dofirst表示，在扫描时不会执行其中的代码块，在真正运行的时候才会执行
    doFirst {
        println("woshou")
    }
}.dependsOn("helloworld")

task("bye") {
    println("bye")
}.dependsOn("woshou")

// 增量更新
task("getsrcname") {
    // 把所有源代码的文件名记录下来
    // 增量更新讲解：input是该任务的输入，output是输出，在执行任务前
    // 扫描到输入或者输出的内容没有任何变化时，就不会执行后面的操作，直接完成任务
    // 如果有任何变化，就会重新执行任务
    inputs.dir("src")
    outputs.file("info.txt")
    doFirst {
        var files = fileTree("src")
        var file = file("info.txt")
        files.forEach {
            if (it.isFile) {
                Thread.sleep(1000)
                file.appendText(it.absolutePath)
                file.appendText("\r\n")
            }
        }
    }
}
