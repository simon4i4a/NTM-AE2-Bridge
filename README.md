# NTM-AE2-Bridge

A Minecraft addon for connecting the HE energy system from **HBM's NTM: Decentralized Edition** to **Applied Energistics 2** ME networks.

## Target environment

- Minecraft 1.21.1
- NeoForge 21.1.249
- HBM: NTM Decentralized Edition (NeoForge build for Minecraft 1.21.1)
- Applied Energistics 2 19.2.18 (NeoForge build for Minecraft 1.21.1)
- Java 21
- Gradle 8.10.2

Compatibility is a development target. The complete mod still needs a successful local compile and in-game verification against the exact dependency JARs.

## Current source implementation

- Registered HE–AE converter block, block entity, and placeable block item.
- Converter appears in the Functional Blocks creative tab.
- HBM HE receiver capability using `IEnergyHandlerMK2`.
- Internal HE buffer with block-entity persistence.
- AE2 managed grid node and server-side energy injection.
- Simulated ME injection before committing energy; the buffer is debited only for energy actually accepted.
- Isolated conversion math with JUnit 5 unit tests and a dependency-free smoke test.
- GitHub Actions validates JSON resources, model texture references, and isolated conversion math.
- Common config for conversion ratio, transfer limit, energy loss, and buffer capacity.
- Dedicated read-only GUI displays the HE buffer, capacity, ME network connection state, conversion ratio, transfer limit, and energy loss.
- Horizontal placement orientation rotates the converter's front panel to face the player.
- Custom industrial textures and a layered block model.
- Buffer contents are clamped safely if the configured capacity is reduced.
- Basic crafting recipe and block loot table.
- English and Russian translations.

## Full build: local setup

The third-party HBM and AE2 JARs are not redistributed by this repository. You must obtain the correct files yourself and make sure they are compatible with **Minecraft 1.21.1 + NeoForge 21.1.249**.

1. Install a 64-bit Java 21 JDK.
2. Install Gradle 8.10.2 (or use a compatible Gradle 8.x installation).
3. Create a folder named `libs` in the project root.
4. Put the NeoForge JAR for AE2 19.2.18 and the NeoForge JAR for HBM NTM into `libs/`.
5. Keep the original JAR filenames. The build preflight reads their mod metadata and checks for mod IDs `ae2` and `hbm`.

Expected layout:

```text
NTM-AE2-Bridge/
├── libs/
│   ├── <AE2 1.21.1 NeoForge JAR>
│   └── <HBM 1.21.1 NeoForge JAR>
├── build.gradle
├── settings.gradle
└── gradle.properties
```

Run these commands from the project root:

**Windows (PowerShell or Command Prompt):**

```powershell
java -version
gradle --version
gradle verifyLocalModDependencies
gradle verifyResources
gradle clean build
```

**Linux / macOS:**

```bash
java -version
gradle --version
gradle verifyLocalModDependencies
gradle verifyResources
gradle clean build
```

The dependency preflight runs automatically before Java compilation and gives a direct error if AE2 or HBM is missing or its mod ID cannot be found. The output JAR will be under `build/libs/`.

## Running a development client

After a successful build, start the NeoForge development client with:

```bash
gradle runClient
```

The development server can be started with:

```bash
gradle runServer
```

Use a separate test instance/world and install the same compatible HBM and AE2 versions there. Do not test the first build in a valuable survival world.

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

## Automated checks and known limitations

GitHub Actions currently runs:
- Gradle resource validation (JSON parsing and model-to-texture reference checks).
- Dependency-free conversion math compilation and smoke checks.

The hosted CI does **not** compile the complete mod because the proprietary/third-party HBM and AE2 development JARs are not stored in this repository. A complete `gradle clean build` must be run locally after placing those JARs in `libs/`.

## Project status

Work in progress. The full source has not yet been confirmed to compile against the exact AE2 19.2.18 and HBM 1.21.1 JARs, and the mod has not yet been tested in-game. API compatibility, energy transfer behavior, GUI rendering/synchronization, and recipe balancing still require verification.
