# HCF-Core
### SI VOUS VOULEZ REUTILISER LE PLUGIN CONTACTEZ "Hugo#0001 SUR DISCORD ou @Rhodless SUR TWITTER 
### Projet abandonné depuis mars 2021
HCF-Core basique qui a été développé en début 2021, les factions sont gérés en [YMAL](https://en.wikipedia.org/wiki/YAML) (il est recommandé de changer ce mode de stockage en base de données, MongoDB dans l'idéal pour les performances). Le système de scoreboard est [FastBoard](https://github.com/MrMicky-FR/FastBoard) (il est recommandé de changer pour des performances optimales, je recommande [Assemble](https://github.com/ThatKawaiiSam/Assemble) ou [Frame](https://github.com/thundereye2k/Frame)).
<h4> Je rappel que ce plugin est abandonné depuis longtemps et qu'il n'y aura aucun support et aucun changement (contacter "Hugo#0001 sur Discord ou @Rhodless sur Twitter en cas de question ou d'aide) </h4>

# Configuration
<p>Tous les messages, scoreboard, positions (spawn et roads) sont configurables depuis les fichiers yml :</p>

<ul>
  <li><a href="https://haste.flaxeneel2.net/qMF4SXO1yRZX2MhpEbSu5kEei6YE72C58a2yJYno4HB2OwL1w5.yaml">cooldowns.yml</a></li>
  <li><a href="https://haste.flaxeneel2.net/sdqCcmyOnv0huvU654Pc20r9Di26yBKUHCf7j4UogDynFeeLJ3.yaml">lang.yml</a></li>
  <li><a href="https://haste.flaxeneel2.net/0wUtBToKWbvlua5M8rEOoPTlcOpiFWt30vgSTK7Cuz1LbmQN7F.yaml">locations.yml</a></li>
  <li><a href="https://haste.flaxeneel2.net/JwbhHTh6VqOgTBi9LOJYGzwD7N3K7fQEZEJBn0Tp1BiZvf2htq.yaml">scoreboard.yml</a></li>
</ul>

# Avancement
Le plugin est terminé à environ 50%, une grosse partie du système de faction a été effectuée (sauf le système de claim) et les commandes sont finies à environ 60%.
L'end, le nether, le système d'events, et de dtr ne sont pas faits (toutes les informations pour une faction sont terminés, comme les points, le dtr ou la balance).
Le système de road (dans l'overworld) est terminé. Le système de territoire est terminé (les messages de "Entering <road>", sauf pour les factions.

### Class importants pour voir l'avancée:
<ul>
  <li><a href="https://github.com/Rhodless/HCF-Core/blob/main/src/main/java/fr/rhodless/hcfcore/faction/Territory.java">Territory.java</a></li>
  <li><a href="https://github.com/Rhodless/HCF-Core/blob/main/src/main/java/fr/rhodless/hcfcore/faction/Faction.java">Faction.java</a></li>
  <li><a href="https://github.com/Rhodless/HCF-Core/blob/main/src/main/java/fr/rhodless/hcfcore/player/FPlayer.java">FPlayer.java</a></li>
  <li><a href="https://github.com/Rhodless/HCF-Core/blob/main/src/main/java/fr/rhodless/hcfcore/player/HCFClass.java">HCFClass.java</a></li>
  <li><a href="https://github.com/Rhodless/HCF-Core/blob/main/src/main/java/fr/rhodless/hcfcore/player/Data.java">Data.java</a></li>
</ul>

# Class (enums) importantes sur la gestion des fichiers YML
<ul>
  <li><a href="https://github.com/Rhodless/HCF-Core/blob/main/src/main/java/fr/rhodless/hcfcore/file/FileManagement.java">FileManagement.java</a></li>
  <br>
  <li><a href="https://github.com/Rhodless/HCF-Core/blob/main/src/main/java/fr/rhodless/hcfcore/file/CooldownsValues.java">CooldownsValues.java</a></li>
  <li><a href="https://github.com/Rhodless/HCF-Core/blob/main/src/main/java/fr/rhodless/hcfcore/file/LangValues.java">LangValues.java</a></li>
  <li><a href="https://github.com/Rhodless/HCF-Core/blob/main/src/main/java/fr/rhodless/hcfcore/file/LocationsValues.java">LocationsValues.java</a></li>
  <li><a href="https://github.com/Rhodless/HCF-Core/blob/main/src/main/java/fr/rhodless/hcfcore/file/ScoreboardValues.java">ScoreboardValues.java</a></li>
</ul>
