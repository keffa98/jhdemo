export interface IEmplacement {
  id?: number;
  nomEplacement?: string;
}

export class Emplacement implements IEmplacement {
  constructor(public id?: number, public nomEplacement?: string) {}
}
