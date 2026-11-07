# NTM-AE2-Bridge

A Minecraft addon for connecting the HE energy system from **HBM's NTM: Decentralized Edition** to **Applied Energistics 2** ME networks.

## Target environment

- Minecraft 1.21.1
- NeoForge 21.1.249
- HBM: NTM Decentralized Edition
- Applied Energistics 2 19.2.18

Compatibility is a development target and still needs to be verified in-game.

## Current source implementation

- Registered HE–AE converter block, block entity, and placeable block item.
- Converter appears in the Functional Blocks creative tab.
- HBM HE receiver capability using `IEnergyHandlerMK2`.
- Internal HE buffer with block-entity persistence.
- AE2 managed grid node and server-side energy injection.
- Simulated ME injection before committing energy; the buffer is debited only for energy actually accepted.
- Common config for conversion ratio, transfer limit, energy loss, and buffer capacity.
- Empty-hand interaction displays buffer level and ME connection status.
- Basic crafting recipe and block loot table.
- English and Russian translations.

## Configuration

The common config file is generated under the NeoForge config directory:

```toml
[conversion]
ae_per_he = 1.0
max_transfer = 256
energy_loss = 0.0
buffer_capacity = 10000
```

- `ae_per_he`: AE energy produced per HE before energy loss.
- `max_transfer`: maximum HE accepted from HBM and processed per game tick.
- `energy_loss`: fraction of energy lost during conversion, from 0.0 to 1.0.
- `buffer_capacity`: internal buffer size in HE.

## Crafting

The initial recipe is a placeholder recipe using iron, redstone, quartz, and a diamond. Ingredient balancing may change.

## Development setup

Use Java 21 and place the matching HBM and AE2 development/runtime JARs in the project-local `libs/` directory. The directory is ignored by Git and the JARs are not redistributed by this repository.

Run the Gradle `build` task after adding both dependencies.

## Project status

Work in progress. Source implementation is in place, but it has **not yet been compiled or tested in-game**. API compatibility, energy transfer behavior, GUI, final textures, and recipe balancing still need verification.
