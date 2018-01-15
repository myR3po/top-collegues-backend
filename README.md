Application Backend Top Collèges
================================



Ressource Collegues
------------------------


URI path                                               |  méthode HTTP  |  Description     
------------------------------------------------------ | -------------- | --------------------------------------------------------------------------
**_/collegues_**                                       |  GET           |  retourne la liste des collègues au format JSON
**_/collegues_**                                       |  POST          |  enregistre un nouveau collègue et retourne la liste des collègues à jour au format JSON


Ressource Votes
---------------


URI path                                               |  méthode HTTP  |  Description     
------------------------------------------------------ | -------------- | --------------------------------------------------------------------------
**_/votes_**                                           |  GET           |  retourne la liste des trois derniers votes au format JSON
**_/votes?since={VOTE_ID}_**                           |  GET           |  retourne la liste des derniers votes depuis le vote ayant l'id {VOTE_ID} au format JSON
**_/votes/_**                                          |  PATCH         |  Ajoute un vote et retourne le collègue


