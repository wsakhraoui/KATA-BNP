# kata-wahid
Test Technique Live Coding

Problématique : 
1.	Développer un service Web Rest qui permet d'accepter une liste de structure de données appelée Person telle que définie ci-dessous, avec un paramètre du type entier subListSize. 
a. Les objets Person sont considérés valides en fonction des critères suivants: 
i. LastName : obligatoire et différent de vide. 
ii. FirstName : obligatoire et différent de vide. 

b. Le service doit pouvoir diviser (Split) la liste des Person en une seule liste de sous-listes dont la taille de chacune des sous-listes est égale au maximum à subListSize. 

2.	Filtrer et Sauvegarder tous les objets Person reçus dans une table (DB H2 par exemple) comme suit : 

a. Filtrer la liste des Person lorsque Age > 40. 
b. Sauvegarder la liste dans la table. 
c. La liste filtrée doit apparaître dans un fichier texte avec un chemin paramétrable. 
i. On doit trier la liste avant qu'elle ne soit écrite en se basant sur le triplet suivant : (AgeLastName FirstName) 

3.	Un ensemble de tests unitaires pour tester le service développé. 

Exemple du body de la requête : 
{ 
  "Persons": [ 
      { 
        "FirstName": "P1", 
        "LastName": "P2", 
        "Age": 10 
      }, 
      { 
        "FirstName": "P1", 
        "LastName": "P2", 
        "Age": 10 
       } 
   ], 
   "SubListSize": 2 
}
