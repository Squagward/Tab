Example Fabric 1.18.2 Mod
========================
Contains:
- Essential
- QuiltFlower decompiler
- Kotlin
- If you want DevAuth, just add the jar to run/mods.
  - [Here's the link](https://github.com/DJtheRedstoner/DevAuth/releases/latest)

# VM args
```
-Dfabric.dli.config=PATH TO YOUR DIRECTORY\.gradle\loom-cache\launch.cfg
-Dfabric.dli.env=client
-Dfabric.dli.main=net.fabricmc.loader.impl.launch.knot.KnotClient
-Ddevauth.enabled=true (if you want DevAuth)
```
