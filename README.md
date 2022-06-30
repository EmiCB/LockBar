# LockBar
[![GitHub release (latest by date)](https://img.shields.io/github/v/release/EmiCB/LockBar?label=download&logo=github)](https://github.com/EmiCB/LockBar/releases/latest)

A Minecraft plugin that locks inventory slots. Locking a slot disables moving, dragging, dropping, and block placement
for that slot.

_**Requires Java 11+**_

## Building
Compile a `.jar` from the commandline by doing an `install` via Maven:
```
$ mvn install
```
It should show up in the target directory. Make sure to update your version number.

---

## Commands

| Command                               | Description                                          |
|---------------------------------------|------------------------------------------------------|
| `/lock-all`                           | locks entire inventory for all players               |
| `/lock-bar`                           | locks entire hot bar for all players                 |
| `/lock-slot <begin> <optional_end>`   | locks the specified range of slots for all players   |
| `/unlock-slot <begin> <optional_end>` | unlocks the specified range of slots for all players |

## Config

| Key            | Type           | Description                                        |
|----------------|----------------|----------------------------------------------------|
| `lock-all`     | `boolean`      | enable / disable global inventory lock             |
| `lock-bar`     | `boolean`      | enable / disable global hotbar lock                |
| `locked-slots` | `integer list` | list of all currently globally locked hotbar slots |

**Example:** only the first two hotbar slots will be locked

```yaml
lock-all: false
lock-bar: false
locked-slots:
- 0
- 1
```

## Known Issues
- Bukkit API makes stopping item drops from specific slots difficult to detect. My current workaround compares the 
dropped item type to the item type(s) in the locked slot(s). This means that **dropping items from an unlocked slot will
not work if it is the same type of item that is present in a locked slot.** I am still working on finding a better
solution.
- Stopping block placement when using specified slots is also not yet supported.