import {OpinionDTO} from "./opinionDTO";
import {Book} from "./book";

export interface BookAndOpinion {
  book: Book;
  opiniones: OpinionDTO[];
}
