# NTM-AE2-Bridge

A Minecraft addon for connecting the HE energy system from **HBM's NTM: Decentralized Edition** to **Applied Energistics 2** ME networks.

## Target environment

- Minecraft 1.21.1
- NeoForge 21.1.249
- HBM: NTM Decentralized Edition
- Applied Energistics 2 19.2.18

Compatibility is a development target and still needs to be verified in-game.

## Current implementation

- Registered HE–AE converter block and block entity.
- HBM HE receiver capability on the converter.
- Internal HE buffer with persistent block-entity storage.
- AE2 grid node and server-side energy injection.
- Simulation before injection, with HE consumed only for AE energy actually accepted.
- Common configuration for conversion ratio, transfer limit, energy loss, and buffer capacity.
- English and Russian block names.

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
- `max_transfer`: maximum HE processed per game tick.
- `energy_loss`: fraction of energy lost during conversion, from 0.0 to 1.0.
- `buffer_capacity`: internal buffer size in HE.

## Development setup

Use Java 21 and place the matching HBM and AE2 development/runtime JARs in the project-local `libs/` directory. The directory is ignored by Git and the JARs are not redistributed by this repository.

Run the Gradle `build` task after adding both dependencies.

## Project status

Work in progress. The initial energy path is implemented in source, but the project has not yet been compiled or tested in-game. GUI, recipes, final textures, and broader compatibility testing remain.
