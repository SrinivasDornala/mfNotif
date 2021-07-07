
export class Order {
  id!: number;
  userId!: number;
  name!: string;
  method!: string;
  notify!: string;

  constructor(data: any = {}) {
      this.id = data.id;
      this.userId = data.userId;
      this.name = data.name || '';
      this.method = data.method || '-';
      this.notify = data.notify || 'N';
    }
}
