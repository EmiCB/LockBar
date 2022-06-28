# LockBar
A Minecraft plugin that locks inventory slots.

_**Requires Java 11+**_

---

## Commands

| Command     | Description                         |
|-------------|-------------------------------------|
| `/lock-all` | locks entire inventory for everyone |
| `/lock-bar` | locks entire hot bar for everyone   |

Note: Bukkit API makes stopping item drops from specific slots difficult to detect. My current workaround compares the 
dropped item type to the item type(s) in the locked slot(s). This means that **dropping items from an unlocked slot will
not work if it is the same type of item that is present in a locked slot.** I am still working on finding a better
solution.

## Config

## Building

Build the source with Maven:

```
$ mvn install
```