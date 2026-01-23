```mermaid
flowchart TB
  %% =========================
  %% Clients
  %% =========================
  U[User Browser]:::client

  %% =========================
  %% DNS
  %% =========================
  R53["Route 53 DNS\nHosted Zone: damonincorvaia.com"]:::dns

  %% =========================
  %% Frontend (Amplify)
  %% =========================
  subgraph FE["Frontend: AWS Amplify Hosting"]
    A["Amplify Hosting\n(Build + Deploy from GitHub)"]:::frontend
    CF["CDN (CloudFront behind Amplify)\nHTTPS by default"]:::cdn
    S["Static SPA Assets\nHTML/CSS/JS"]:::frontend
    A --> CF --> S
  end

  %% =========================
  %% Backend (Beanstalk + ALB)
  %% =========================
  subgraph BE["Backend: Elastic Beanstalk (Java API)"]
    ALB["Application Load Balancer\nListener: 443 HTTPS"]:::alb
    ACM["ACM Certificate\napi.games.damonincorvaia.com"]:::acm
    TG["Target Group\nHealth Checks"]:::alb
    subgraph VPC["VPC"]
      subgraph ASG["Auto Scaling Group (min 1)"]
        EC2["EC2 Instance(s)"]:::compute
        APP["Java API (JAR)\n/api/... endpoints"]:::compute
        EC2 --> APP
      end
    end
    ALB --> TG --> EC2
    ACM -. TLS Cert .-> ALB
  end

  %% =========================
  %% DNS routing
  %% =========================
  R53 -->|games.damonincorvaia.com| A
  R53 -->|api.games.damonincorvaia.com| ALB

  %% =========================
  %% User flows
  %% =========================
  U -->|1) Load site| R53
  U -->|2) HTTPS GET games.damonincorvaia.com| CF
  U -->|3) SPA runs in browser| S

  U -->|"4) API calls (VITE_API_BASE_URL) https://api.games.../api/..."| R53
  U -->|5) HTTPS to ALB :443| ALB
  ALB -->|6) Forward to healthy target| TG
  TG -->|7) Request handled by Java API| APP

  %% =========================
  %% Styles
  %% =========================
  classDef client fill:#f7f7f7,stroke:#333,stroke-width:1px;
  classDef dns fill:#fff4cc,stroke:#8a6d3b,stroke-width:1px;
  classDef frontend fill:#e8f5e9,stroke:#2e7d32,stroke-width:1px;
  classDef cdn fill:#e3f2fd,stroke:#1565c0,stroke-width:1px;
  classDef alb fill:#f3e5f5,stroke:#6a1b9a,stroke-width:1px;
  classDef acm fill:#fdecea,stroke:#b71c1c,stroke-width:1px;
  classDef compute fill:#fff,stroke:#555,stroke-width:1px;
```