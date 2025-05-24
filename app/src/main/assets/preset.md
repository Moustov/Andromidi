Sending PRST files to Matribox
===

# PRST files
## Description
1. PRST files are base64 encoded files  
    > WzMsMiwwLDAsMTYsMTEsMC ...

    decoded string :
    > [3, 2, 0, 0, 16, 11, ...
2. Decoded files provide integer values
    > ````
    > 91 51 44 50 44 48 44 ...
    > ````
3. Those integers are ASCII codes -  
   1. This defines a list in '[' ']' with values separated with commas  
   2. @31-@47: the effect name can be found - a '0' will define the end of the string
   >    ````
   >    31 A
   >    32 C
   >    33 \32 
   >    34 B
   >    35 a
   >    36 s
   >    37 s
   >    38 \32 
   >    39 S
   >    40 i
   >    41 m
   >    42 \0
   >    ````
   3. @107-@116: the style name can be found  - a '0' will define the end of the string
      > **Styles**: Rock, Pop, Metal, Blues, Country, Jazz, Fusion, Folk, Funk, Acoustic

## Python code to decode a PRST file
````python
import base64


def process_file(file_path) -> list:
    res = []
    # Ouvrir le fichier et lire son contenu
    with open(file_path, 'r') as file:
        encoded_content = file.read()

    # Décoder le contenu en Base64
    decoded_bytes = base64.b64decode(encoded_content)

    # Convertir les bytes en une chaîne et extraire le vecteur
    decoded_string = decoded_bytes.decode('utf-8').strip()

    # Enlever les crochets et séparer les valeurs
    vector_string = decoded_string.strip('[]')
    values = map(int, vector_string.split(','))  # Convertir en entiers

    # Afficher chaque item
    for index, value in enumerate(values):
        ascii_char = chr(value)  if value not in (13, 10, 9, 8, 7) else rf"\{value}"# Convertir la valeur en caractère ASCII
        res.append(f"Rang: {index:03}, Valeur: {value:03}, Caractère: '{ascii_char}'")
    return res

# Exemple d'utilisation
l1 = process_file('c:/tmp/60-C Matribox II PRO.prst')
l2 = process_file('c:/tmp/60-C Matribox II PRO - v1.prst')

for a,b in zip(l1, l2):
    print ("XXX" if a!=b else "   ", a, " " * (40 - len(a)), "|", b)
```` 

# SysEx message