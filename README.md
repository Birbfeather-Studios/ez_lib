
# EzLib

A mod designed to create mods! This library streamlines the process of creating blocks, items and other generic things in Minecraft modding. You can checkout the repository of [OtherMod](https://github.com/Birbfeather-Studios/other_mod) as an example for how to use EzLib as a depenancy, and how to register all of the things yo could make with it so far. 


## Installation

Install this depenancy with Jitpack. Start by putting this line of code into your depenancies in your build.gradle:

```
    dependencies {
	        modImplementation "com.github.Birbfeather-Studios:ez_lib:${ezlib_version}"
	}
```

And add this to your repository section in your build.gradle as well:

```
    repositories {
	    	mavenCentral()
	    	maven { url 'https://jitpack.io' }
	}
```

Finally add this to your gradle.properties file:

```
    ez_lib_version=version_here
```

You will want to replace "version_here" with the most recent [release tag](https://github.com/Birbfeather-Studios/ez_lib/releases) on github for EzLib.
