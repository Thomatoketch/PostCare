
# 5.6 Réalisation technique de l’application

Le développement de POSTCARE s’est appuyé sur une architecture modulaire, pensée pour séparer les responsabilités entre les composants de l’interface utilisateur, les traitements métiers et les appels au modèle d’intelligence artificielle.

## Côté mobile (Kotlin – Android)

- **Architecture** : L'application repose sur le patron MVVM (Model-View-ViewModel) pour assurer une meilleure maintenabilité du code. Les interactions utilisateur sont gérées par des `Activity` et `Fragment`, la logique métier est encapsulée dans des `ViewModel`, et les données dans des classes dédiées (`data class`, `Repository`, etc.).

- **Interfaces dynamiques** : Plusieurs écrans adaptent leur contenu selon le type d’utilisateur (patient ou médecin). Nous utilisons des `RecyclerView` pour gérer dynamiquement la liste des patients ou des données de suivi, avec des mises à jour en temps réel.

- **Analyse d’image par IA** : Nous avons intégré un modèle TensorFlow Lite (`model.tflite`) directement embarqué dans l’application. Ce modèle, initialement entraîné sur des mammographies open source, est utilisé en local pour classifier des images (cancer / non cancer) à partir de photos importées par le patient. L'image est prétraitée (mise à l’échelle, conversion en niveaux de gris) avant d’être transmise au modèle.

- **Expérience utilisateur adaptée** : Chaque écran est conçu avec des contraintes fortes d’accessibilité (boutons larges, contraste élevé, police lisible, navigation simplifiée). Un écran de **pilulier interactif** permet par exemple de cocher les médicaments pris à chaque moment de la journée.

## Côté données

- **Stockage local** : Nous utilisons une base de données locale (SQLite via Room ou préférences partagées selon les cas) pour conserver les données saisies par le patient, avec possibilité de synchronisation future.

- **Système d’alerte intelligent** : Une logique embarquée détecte automatiquement les valeurs anormales saisies par le patient (douleur, température, cicatrisation). Ces alertes sont envoyées en temps réel au médecin sous forme de notification ou de priorité dans son tableau de bord.
