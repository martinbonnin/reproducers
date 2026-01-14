plugins {
  id("com.gradleup.nmcp.settings")
//  id("com.gradle.develocity")
}

configure<nmcp.NmcpSettings> {
  centralPortal {
    username.set(providers.gradleProperty("mavenCentralUsername"))
    //...
  }
}

//develocity {
//
//}