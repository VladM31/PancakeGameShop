export class QueryBuilder {
    constructor(baseURL) {
        this.baseURL = baseURL;
        this.path = "";
        this.params = new URLSearchParams();
    }

    setPath(path) {
        this.path = path;
        return this;
    }

    addParam(key, value) {
        this.params.append(key, value);
        return this;
    }

    build() {
        const url = new URL(this.baseURL);
        url.pathname = url.pathname + this.path;
        url.search = this.params.toString();
        return url;
    }
}