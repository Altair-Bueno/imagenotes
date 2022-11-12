# Image Notes

Una aplicación para registrar fotos con fecha y hora. Util para registrar que las
aulas de la ETSI se cierran adjuntando la foto de la puerta.

## Detalles técnicos

- Cuenta con 3 actividades (paquete `es.uma.imagenotes.activity`)
- Traducción al ingles y español
- Uso de `Toast` para mostrar información al usuario
- Uso de SQLite a través de [Room](https://developer.android.com/training/data-storage/room#java)
- Uso de la cámara para registrar las notas
- `RecyclerView` para generar la lista de notas

## Anotaciones sobre la entrega

- El proyecto ha sido probado en un emulador Pixel 3a con sdk 33
- En ocasiones el emulador tiene problemas para iniciar la cámara. Se recomiendo hacer
  un `Cool boot` en caso de que la aplicación de cámara no funcione. 
