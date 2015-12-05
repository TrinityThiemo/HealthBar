How to configure HealthBar plugin:

Default Configuration:
------------------------------------------------------
format: "&f%damager - &c[%health&c]"
health-format:
  health-used: "&c|"
  health-not-used: "&4|"

disabled-worlds:
- my_world_name

disabled-entities:
- ender_dragon
- wither
------------------------------------------------------

format:
How to full bar should look like. In here %damager is replaced by the player name, or by the attacked entitiy type, %health is being replaced with the entity his health.

health-format:
What the health should look like, health-used is the health that the entity still has, and health-not-used is the health that is already removed. (The damage that the player already took)

disabled-worlds:
Worlds where the HealthBar is disabled.

disabled-entities:
Entities that should not show the healthbar.
