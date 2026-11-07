# NTM-AE2-Bridge

A Minecraft addon for connecting the HE energy system from **HBM's NTM: Decentralized Edition** to **Applied Energistics 2** ME networks.

## Target environment

- Minecraft 1.21.1
- NeoForge 21.1.x
- HBM: NTM Decentralized Edition
- Applied Energistics 2 19.2.18

Compatibility is a development target and still needs to be verified in-game.

## Planned features

- A converter block with HBM HE input and AE2 ME network output.
- Configurable HE-to-AE conversion ratio.
- Transfer-rate limit and optional conversion loss.
- Internal energy buffer to prevent energy loss when the ME network cannot accept power.
- Server-side transfer logic and status display.

## Configuration concept

```toml
[conversion]
ae_per_he = 1.0
max_transfer = 256
energy_loss = 0.0
buffer_capacity = 10000
```

## Project status

Work in progress. The API integration and project scaffold are being developed before the converter block, recipes, textures, and interface.
