export interface Opinion {
  opinion_Id?: number;
  usuario_Id: number;
  libro_Id: number;
  calificacion: number;
  comentario: string;
  fecha_Opinion: Date;
}
